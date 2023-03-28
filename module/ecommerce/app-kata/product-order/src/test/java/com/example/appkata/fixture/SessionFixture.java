package com.example.appkata.fixture;


import com.example.appkata.module.account.application.dto.CreateAccountRequest;
import com.example.appkata.module.account.domain.Account;
import com.example.appkata.module.login.application.LoginService;
import com.example.appkata.module.login.application.dto.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.servlet.http.HttpSession;


import static com.example.appkata.fixture.AccountFixture.*;
import static com.example.appkata.module.account.application.AccountSessionManager.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@Component
public class SessionFixture {

    private final AccountFixture accountFixture;
    private final LoginService loginService;

    public SessionFixture(AccountFixture accountFixture, LoginService loginService) {
        this.accountFixture = accountFixture;
        this.loginService = loginService;
    }

    public void createSessionUser() {
        Account account = accountFixture.createAccount();
        loginService.login(new LoginRequest(account.getEmail()));
    }

    public static MockHttpSession getLoginSession(MockMvc mockMvc, ObjectMapper objectMapper) throws
            Exception {
        CreateAccountRequest request = new CreateAccountRequest(FIXTURE_USER_NAME, FIXTURE_USER_EMAIL);

        mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andDo(print());


        MvcResult mvcResult = mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new LoginRequest(FIXTURE_USER_EMAIL)))
        ).andReturn();

        HttpSession session = mvcResult.getRequest().getSession();

        MockHttpSession mockSession = new MockHttpSession();
        mockSession.setAttribute(LOGIN_USER_KEY, session.getAttribute(LOGIN_USER_KEY));

        return mockSession;
    }
}
