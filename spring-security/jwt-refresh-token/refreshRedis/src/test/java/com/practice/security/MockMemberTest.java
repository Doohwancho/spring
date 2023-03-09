package com.practice.security;

import com.practice.dto.JoinDto;
import com.practice.service.member.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class MockMemberTest {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MockMemberTest.class);

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MemberService memberService;

    protected MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();

        JoinDto joindto = JoinDto.builder()
                .username("username")
                .password("password")
                .intro("intro")
                .build();

       memberService.register(joindto);
    }

    @Test
    @WithMockUser(username = "username", roles = "USER")
    @DisplayName("refresh token을 이용하여 jwt-access-token과 refresh-token을 재발급 받을 수 있다.")
    void shouldRefreshJwtAccessTokenByRefreshToken() throws Exception {
        //given
        ResultActions loginResponse = mockMvc.perform(post("/member/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"username\",\"password\":\"password\"}"));

        String refreshToken = loginResponse.andReturn().getResponse().getCookie("RefreshToken").getValue();

        //when
        ResultActions reissueResponse = mockMvc.perform(MockMvcRequestBuilders
                .post("/member/reissue")
                .cookie(loginResponse.andReturn().getResponse().getCookie("RefreshToken"))
                .contentType(MediaType.APPLICATION_JSON));

        //then
        loginResponse
                .andExpect(status().isOk())
                .andExpect(cookie().exists("RefreshToken"))
                .andExpect(jsonPath("$.grantType").value("Bearer-"))
                .andExpect(jsonPath("$.accessToken").exists());

        reissueResponse
                .andExpect(status().isOk());
    }


//    @Test
//    public void testProtectedResourceWithPrincipal() {
//        // Create a test user with the appropriate authentication and authorization credentials
//        UserDetails userDetails = User.withUsername("test_user")
//                .password("test_password")
//                .authorities("ADMIN")
//                .build();
//        // Set the authentication for the test user
//        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        // Call the endpoint with the Principal parameter
//        String result = protectedResourceController.testEndpoint(Principal principal);
//
//        // Assert the result
//        Assert.assertEquals("success", result);
//    }


    //토큰 만료 테스트
//    @DisplayName("2. 토큰 만료 테스트 ")
//    @Test
//    void test_2() throws InterruptedException {
//        TokenBox token = getToken();
//
//        Thread.sleep(3000);
//        HttpHeaders header = new HttpHeaders();
//        header.add(HttpHeaders.AUTHORIZATION, "Bearer "+token.getAuthToken());
//        RestTemplate client = new RestTemplate();
//        assertThrows(Exception.class, ()->{
//            HttpEntity body = new HttpEntity<>(null, header);
//            ResponseEntity<String> resp2 = client.exchange(uri("/greeting"), HttpMethod.GET, body, String.class);
//        });
//
//        token = refreshToken(token.getRefreshToken());
//        HttpHeaders header2 = new HttpHeaders();
//        header2.add(HttpHeaders.AUTHORIZATION, "Bearer "+token.getAuthToken());
//        HttpEntity body = new HttpEntity<>(null, header2);
//        ResponseEntity<String> resp3 = client.exchange(uri("/greeting"), HttpMethod.GET, body, String.class);
//
//        assertEquals("hello", resp3.getBody());
//    }
}
