package com.practice.service.member;


import com.practice.config.redis.cache.CacheKey;
import com.practice.domain.Member;
import com.practice.domain.token.LogoutAccessToken;
import com.practice.domain.token.RefreshToken;
import com.practice.dto.JwtTokenDto;
import com.practice.dto.MemberDto;
import com.practice.exception.message.ExceptionMessage;
import com.practice.exception.model.TokenCheckFailException;
import com.practice.exception.model.UserAuthException;
import com.practice.model.LoginModel;
import com.practice.model.MemberModel;
import com.practice.repository.member.MemberRepository;
import com.practice.service.token.LogoutAccessTokenService;
import com.practice.service.token.RefreshTokenService;
import com.practice.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.security.Principal;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    private final JwtTokenUtil jwtTokenUtil;

    private final RefreshTokenService refreshTokenService;

    private final LogoutAccessTokenService logoutAccessTokenService;

    @Transactional(readOnly = true)
    @Override
    public JwtTokenDto login(LoginModel loginModel) {

        //TODO - important! login할 떄, jwt token 만들고 http response에 담아 보내는 동시에, refresh token을 db에 저장 후, 쿠키에 담아 보내는 과정.
        //step1) authenticate
        Member member = authenticate(loginModel);

        //step2) generate jwt token with exp_time of 1hr
        String token = this.jwtTokenUtil.generateToken(member.getUsername(), JwtTokenUtil.ACCESS_TOKEN_EXPIRE_TIME); //TODO - login할 때, jwt 토큰 주는데, expire time 붙여서 주는구나.

        //step3) save refresh token to database with exp_time of 6hr
        //TODO - 근데 jwt-access-token만 쓰지, 왜 굳이 refresh token쓰면서 db에 io까지 해가며 해야함?
        //A. 1. 사용자 강제 로그아웃 기능
        //   2. 유저 차단
        //   3. 토큰 탈취시 대응
        //..한다는 가정 하에 jwt token + refresh token 겸해서 쓴다.
        RefreshToken refreshToken = refreshTokenService.saveRefreshToken(member.getUsername(), JwtTokenUtil.REFRESH_TOKEN_EXPIRE_TIME);

        //step4) add refresh token to cookie (cookie also has exp_time same as refresh token)
        jwtTokenUtil.setRefreshTokenAtCookie(refreshToken);
        //TODO - 왜 refresh token은 cookie에 담고, jwt-access-token은 json payload에 담아 보냄?
        //A. refreshToken을 secure httpOnly 쿠키로, accessToken은 JSON payload로 받아와서 웹 어플리케이션 내 로컬 변수로 이용
        //   이를 통해 CSRF 취약점 공격 방어하고, XSS 취약점 공격으로 저장된 유저 정보 읽기는 막을 수 있음
        //   하지만 XSS 취약점을 통해 API 콜을 보낼 때는 무방비하니 XSS 자체를 막기 위해 서버와 클라이언트 모두 노력해야 함

        //step5) http response에서는 jwt token만 담아 보냄. cookie엔 refresh token이 있고.
        return JwtTokenDto.from(token);
    }

    /**
     * Cacheable의 동작 방식은 캐시에서 메서드의 파라미터로 캐시를 먼저 조회합니다.
     * 제가 사용한 Redis에 데이터가 있을 경우는 Redis에 저장된 데이터를 그대로 반환해주고, 없을 경우는 DB에 직접 조회합니다.
     * Cacheable을 통해 저장된 데이터는 어노테이션에 설정된 value::key 의 형태로 Redis의 Key로 저장됩니다.
     * 그에 대한 값은 CustomUserDetails의 형태로 저장됩니다.
     */
    //TODO - 토큰을 가지고 요청할 때 마다 DB에서 회원을 조회하는 것을 줄이기 위해 Cacheable 어노테이션을 이용 가능하다.
    @Cacheable(value = CacheKey.USER, key = "#username", unless = "#result == null")
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("회원 정보를 찾을 수 없습니다."));
    }


    @Transactional
    public Long register(MemberModel memberModel) {
        String username = memberModel.getUsername();

        if (this.memberRepository.existsByUsername(username)) {
            throw new UserAuthException("이미 존재하는 회원입니다.");
        }

        memberModel.setPassword(this.passwordEncoder.encode(memberModel.getPassword()));
        return memberRepository.save(Member.from(memberModel)).getId();
    }

    public Member authenticate(LoginModel loginModel) {
        String username = loginModel.getUsername();

        if (!this.memberRepository.existsByUsername(username)) { //TODO - 이렇게 안하고, 밑에 .findByUserName()으로 한번 db io로 검증하면 되잖아?
            throw new UserAuthException("회원 정보를 찾을 수 없습니다");
        }

        Member member = this.memberRepository.findByUsername(username).get();

        if (!this.passwordEncoder.matches(loginModel.getPassword(), member.getPassword())) {
            throw new UserAuthException(ExceptionMessage.MISMATCH_PASSWORD);
        }
        return member;
    }

    @CacheEvict(value = CacheKey.USER, key = "#username")
    public void logout(String accessToken) {
        String username = jwtTokenUtil.parseToken(jwtTokenUtil.resolveToken(accessToken));
        accessToken = jwtTokenUtil.resolveToken(accessToken);
        long remainTime = jwtTokenUtil.getRemainTime(accessToken); //logout 시, jwt-access-token의 남은 시간만큼 까서 저장함.
        refreshTokenService.deleteRefreshTokenById(username);
        logoutAccessTokenService.saveLogoutAccessToken(LogoutAccessToken.from(username, accessToken, remainTime));
    }

    @Override
    public JwtTokenDto reissue(String refreshToken, Principal principal) {
        //TODO - reissue refresh token 할 떄, 어떤 일이 벌어지는가?
        //step1. refresh token이 spring security context가 관리하는 Principal에 해당하는지 확인
        if (principal == null || principal.getName() == null) {
            throw new UserAuthException(ExceptionMessage.NOT_AUTHORIZED_ACCESS);
        }

        //step2. Principal.name으로 db에서 관리하던 refresh token 가져옴
        String curUserName = principal.getName();
        RefreshToken redisRefreshToken = refreshTokenService.findRefreshTokenById(curUserName); //redis에 넣나보네?

        //step3. 만약 refresh token이 db에 없었다면, throw error
        if (refreshToken == null || !refreshToken.equals(redisRefreshToken.getRefreshToken())) {
            throw new TokenCheckFailException(ExceptionMessage.MISMATCH_TOKEN);
        }

        //step4) refresh token 생성
        return createRefreshToken(refreshToken, curUserName);
    }

    @Transactional(readOnly = true)
    @Override
    public MemberDto findMemberById(String username) {
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new UserAuthException(ExceptionMessage.USER_NOT_FOUND));
        return MemberDto.from(member);
    }

    private JwtTokenDto createRefreshToken(String refreshToken, String username) {
        //step1) 만약 refresh token의 reissue exp_time인 3시간이 아직 안지났으면,
        if (lessThanReissueExpirationTimesLeft(refreshToken)) {
            //step2) 새로운 jwt-access-token을 만들고
            String accessToken = jwtTokenUtil.generateToken(username, JwtTokenUtil.ACCESS_TOKEN_EXPIRE_TIME);

            //step3) refresh token을 db에 Principal.name으로 다시 새롭게 저장하고,
            //TODO - redis를 왜 쓰지?
            RefreshToken newRedisToken = refreshTokenService.saveRefreshToken(username, JwtTokenUtil.REFRESH_TOKEN_EXPIRE_TIME);

            //ste4) cookie에 refresh token 담아서 던진다.
            jwtTokenUtil.setRefreshTokenAtCookie(newRedisToken);
            return JwtTokenDto.from(accessToken);
        }
        return JwtTokenDto.from(jwtTokenUtil.generateToken(username, JwtTokenUtil.ACCESS_TOKEN_EXPIRE_TIME));
    }

    private boolean lessThanReissueExpirationTimesLeft(String refreshToken) {
        return jwtTokenUtil.getRemainTime(refreshToken) < JwtTokenUtil.REISSUE_EXPIRE_TIME;
    }
}
