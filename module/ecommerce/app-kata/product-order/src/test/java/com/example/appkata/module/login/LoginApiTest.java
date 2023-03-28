package com.example.appkata.module.login;

import com.example.appkata.fixture.AccountFixture;
import com.example.appkata.module.login.application.dto.LoginRequest;
import com.example.appkata.module.login.application.dto.LoginResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.servlet.http.HttpSession;

import static com.example.appkata.fixture.AccountFixture.FIXTURE_USER_EMAIL;
import static com.example.appkata.fixture.AccountFixture.FIXTURE_USER_NAME;
import static com.example.appkata.module.account.application.AccountSessionManager.LOGIN_USER_KEY;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@SpringBootTest
@AutoConfigureMockMvc
class LoginApiTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    AccountFixture accountFixture;

    @Test
    void 로그인_요청() throws Exception {
        // given
        accountFixture.createAccount();
        LoginRequest request = new LoginRequest(FIXTURE_USER_EMAIL);


        // when
        MvcResult mvcResult = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andReturn();

        // then
        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        LoginResponse loginResponse = objectMapper.readValue(response.getContentAsString(), LoginResponse.class);
        Assertions.assertThat(loginResponse.getEmail()).isEqualTo(FIXTURE_USER_EMAIL);
        Assertions.assertThat(loginResponse.getUsername()).isEqualTo(FIXTURE_USER_NAME);

        HttpSession session = mvcResult.getRequest().getSession();
        Assertions.assertThat(session.getAttribute(LOGIN_USER_KEY)).isNotNull();
        Assertions.assertThat(session.getAttribute(LOGIN_USER_KEY)).isInstanceOf(Long.class);
    }

}
