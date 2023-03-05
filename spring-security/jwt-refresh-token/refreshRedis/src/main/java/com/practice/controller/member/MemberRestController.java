package com.practice.controller.member;

import com.practice.dto.JwtTokenDto;
import com.practice.exception.message.ExceptionMessage;
import com.practice.exception.model.UserAuthException;
import com.practice.dto.LoginDto;
import com.practice.dto.JoinDto;
import com.practice.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberRestController {
    private final MemberService memberService;

    @GetMapping("/info")
    public ResponseEntity<?> info(Principal principal) {
        if (principal == null || principal.getName() == null) {
            throw new UserAuthException(ExceptionMessage.NOT_AUTHORIZED_ACCESS);
        }
        return ResponseEntity.status(HttpStatus.OK).body(memberService.findMemberById(principal.getName()));
    }

    // 요청 방식은 Content-Type 상관없이 ...
    @PostMapping("/signup")
    public ResponseEntity<Long> signup(@RequestBody JoinDto joinDto) { //TODO - @RequestBody 가 없이 postman으로 body에 json을 넣어서 보내면, 파싱을 못해서, null값으로 들어온다.
        var result = this.memberService.register(joinDto);
        return ResponseEntity.ok(result);
    }

    //TODO - when does POST /member/authorize used?
    //1. global/main 에서, test.html에서, test.js에서,
    //2. init()시, util.js.sendAuthorize()를 보내는데,
    //3. local storage에 있던 jwt-access-token을 보낸다.
    //  3-1. jwt-access-token은 로그인 성공시에 받아 login.js에 보면,
    //      this.setLocalStorage("Authorization", responseValue["grantType"] + responseValue["accessToken"]);
    //      "Bearer jwt-access-token" 으로 local storage에 저장해놨다.
    //
    @PostMapping("/authorize")
    public ResponseEntity<?> authorize(@RequestHeader("Authorization") String accessToken, Principal principal) {
        //TODO - what happen when authorize() with jwt-access-token?
        //1. client에서 로그인 성공해서 jwt-access-token을 받았음
        //2. 근데 사이트 로그아웃은 안했지만, exit함
        //3. jwt-access-token이 expire 되기 전에,  다시 재접함
        //4. 이 때, client->server로, local storage에 보관하던 jwt-access-token을 보내면,
        //5. 서버.authorize()에서, 해당 jwt-access-token이, spring security context에서 관리하는 유저(Principal)의 access token과 같은지 비교하고,
        //6. 같다면, 기존의 access-token을 반환 with HttpStatus.OK, 다르면 throw Exception
        //7. client 측에서 jwt-access-token이 spring-security-context에 보관하는 Principal 정보와 일치함이 확인되면, reissue(jwt-access-token); 를 한다.
        //8. POST /member/reissue 하는데, 이 때 보내는건 jwt-access-token이 아니라, refresh token을 보낸다.
        if (principal == null || principal.getName() == null) {
            throw new UserAuthException(ExceptionMessage.NOT_AUTHORIZED_ACCESS);
        }
        return ResponseEntity.ok(JwtTokenDto.of(accessToken));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.login(loginDto));
    }

    //TODO - what happens when POST /member/logout?
    @PostMapping("/logout")
    public void logout(@RequestHeader("Authorization") String accessToken) {
        memberService.logout(accessToken);
    }

    //TODO - when does POST /member/reissue used?
    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@CookieValue("RefreshToken") String refreshToken, Principal principal) {
        return ResponseEntity.ok(memberService.reissue(refreshToken, principal));
    }
}
