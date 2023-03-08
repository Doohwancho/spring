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
import com.practice.dto.LoginDto;
import com.practice.dto.JoinDto;
import com.practice.repository.member.MemberRepository;
import com.practice.service.token.LogoutAccessTokenService;
import com.practice.service.token.RefreshTokenService;
import com.practice.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    //logger
    private final org.slf4j.Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenUtil jwtTokenUtil;

    private final MemberRepository memberRepository;
    private final RefreshTokenService refreshTokenService;
    private final LogoutAccessTokenService logoutAccessTokenService;


    @Transactional
    public Long register(JoinDto joinDto) {
        String username = joinDto.getUsername();

        if (memberRepository.existsByUsername(username)) {
            throw new UserAuthException("이미 존재하는 회원입니다.");
        }

        joinDto.setPassword(passwordEncoder.encode(joinDto.getPassword()));
        return memberRepository.save(Member.ofUser(joinDto)).getId();
    }

    @Transactional
    public Long registerAdmin(JoinDto joinDto) {
        String username = joinDto.getUsername();

        if (memberRepository.existsByUsername(username)) {
            throw new UserAuthException("이미 존재하는 회원입니다.");
        }

        joinDto.setPassword(passwordEncoder.encode(joinDto.getPassword()));
        return memberRepository.save(Member.ofAdmin(joinDto)).getId();
    }

    @Transactional(readOnly = true)
    @Override
    public JwtTokenDto login(LoginDto loginDto) {

        //TODO - important! login할 떄, jwt token 만들고 http response에 담아 보내는 동시에, refresh token을 db에 저장 후, 쿠키에 담아 보내는 과정.
        //step1) authenticate
        Member member = authenticate(loginDto);

        //step2) generate jwt token with exp_time of 1hr
        String token = jwtTokenUtil.generateToken(member.getUsername(), JwtTokenUtil.ACCESS_TOKEN_EXPIRE_TIME); //TODO - login할 때, jwt 토큰 주는데, expire time 붙여서 주는구나.

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
        return JwtTokenDto.of(token);
    }


    public Member authenticate(LoginDto loginDto) {
        String username = loginDto.getUsername();

        if (!this.memberRepository.existsByUsername(username)) { //TODO - 이렇게 안하고, 밑에 .findByUserName()으로 한번 db io로 검증하면 되잖아?
            throw new UserAuthException("회원 정보를 찾을 수 없습니다");
        }

        Member member = memberRepository.findByUsername(username).get();

        if (!this.passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) {
            throw new UserAuthException(ExceptionMessage.MISMATCH_PASSWORD);
        }
        return member;
    }

    //TODO - username은 중복될 수 있으니까, id로 해야함.
    @CacheEvict(value = CacheKey.USER, key = "#username", condition="#username!=null") //TODO - paramter에 username이 있을 때에만, #username을 인식한다.
    public void logout(String accessToken, String username) {
        String tokenValidatedUsername = jwtTokenUtil.parseToken(jwtTokenUtil.resolveToken(accessToken));
        if(!tokenValidatedUsername.equals(username)) {
            throw new UserAuthException(ExceptionMessage.NOT_AUTHORIZED_ACCESS);
        }
        accessToken = jwtTokenUtil.resolveToken(accessToken);
        long remainTime = jwtTokenUtil.getRemainTime(accessToken); //logout 시, jwt-access-token의 남은 시간만큼 까서 저장함.
        refreshTokenService.deleteRefreshTokenById(tokenValidatedUsername);
        logoutAccessTokenService.saveLogoutAccessToken(LogoutAccessToken.of(tokenValidatedUsername, accessToken, remainTime));
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
        RefreshToken redisRefreshToken = refreshTokenService.findRefreshTokenById(curUserName);

        //step3. 만약 refresh token이 db에 없었다면, throw error
        if (refreshToken == null || !refreshToken.equals(redisRefreshToken.getRefreshToken())) {
            throw new TokenCheckFailException(ExceptionMessage.MISMATCH_TOKEN);
        }

        //step4) refresh token 생성
        return createRefreshToken(refreshToken, curUserName);
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
            return JwtTokenDto.of(accessToken);
        }
        return JwtTokenDto.of(jwtTokenUtil.generateToken(username, JwtTokenUtil.ACCESS_TOKEN_EXPIRE_TIME));
    }

    private boolean lessThanReissueExpirationTimesLeft(String refreshToken) {
        return jwtTokenUtil.getRemainTime(refreshToken) < JwtTokenUtil.REISSUE_EXPIRE_TIME;
    }

    @Transactional(readOnly = true)
    @Override
    public MemberDto findMemberById(String username) {
        Member member = memberRepository.findByUsername(username).orElseThrow(() -> new UserAuthException(ExceptionMessage.USER_NOT_FOUND));
        return MemberDto.of(member);
    }
}


