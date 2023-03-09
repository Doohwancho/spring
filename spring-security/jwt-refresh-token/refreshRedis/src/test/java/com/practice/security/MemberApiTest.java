package com.practice.security;

import com.practice.ApiTest;
import com.practice.config.security.CustomUserDetailService;
import com.practice.domain.Member;
import com.practice.dto.JoinDto;
import com.practice.dto.JwtTokenDto;
import com.practice.repository.member.MemberRepository;
import com.practice.service.member.MemberService;
import com.practice.util.TokenManager;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.practice.fixture.MemberSteps.로그인완료;
import static com.practice.fixture.MemberSteps.일반유저생성;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * list of spring security - jwt & refresh token test cases
 * 0. register --- O
 * 1. login --- O
 * 2. logout -- O
 * 3. access denied
 * 4. access granted
 * 5. refresh token --- O
 * 6. refresh token expired
 * 7. access token --- O
 * 8. access token expired
 * 9. access token invalid
 * 10. access token valid
 * 11. access token revoked
 * 12. access token revoked by admin
 * 13. access token revoked by user
 */
public class MemberApiTest extends ApiTest {

    //logger
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MemberApiTest.class);

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UserDetailsService customUserDetailService;

    @Autowired
    private TokenManager tokenManager;

    @Override
    public void hook() {
    }

    @Test
    @DisplayName("회원가입 테스트")
    void shouldRegister() {
        일반유저생성();
    }

    @Test
    @DisplayName("로그인 테스트")
    void shouldLogin() {
        로그인완료();
    }

    @Test
    @DisplayName("로그인 후 jwt-access-token을 response body에 담아 반환한다.")
    void shouldContainJwtAccessTokenInResponseBodyAfterLogin() {
        //given & when
        ExtractableResponse<Response> response = 로그인완료();

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThat(response.body().as(JwtTokenDto.class)).isNotNull();
        assertThat(response.body().as(JwtTokenDto.class).getGrantType().contains("Bearer-")).isEqualTo(true);
        assertThat(response.body().as(JwtTokenDto.class).getAccessToken()).isNotNull();
    }

    @Test
    @DisplayName("로그인 후 refreshToken을 cookie에 담아 반환한다.")
    void shouldContainRefreshTokenInCookieAfterLogin() {
        //given & when
        ExtractableResponse<Response> response = 로그인완료();

        //then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.SC_OK);
        assertThat(response.cookie("refreshToken")).isNotNull();
    }

    @Test
    @DisplayName("로그아웃 테스트")
    void shouldLogout() {
        //given
        ExtractableResponse<Response> response = 로그인완료();
        String refreshToken = response.cookie("RefreshToken");

        //when
        ValidatableResponse validatableResponse = given().log().all()
                .contentType("application/json")
                .header("Authorization", "Bearer-" + response.body().as(JwtTokenDto.class).getAccessToken()
                ).cookie("RefreshToken", refreshToken)
                .when().post("/member/logout")
                .then().log().all();

        //then
        assertThat(validatableResponse.extract().statusCode()).isEqualTo(HttpStatus.SC_OK);
    }
}
