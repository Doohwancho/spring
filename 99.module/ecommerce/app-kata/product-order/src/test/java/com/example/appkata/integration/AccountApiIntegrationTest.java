package com.example.appkata.integration;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.example.appkata.fixture.AccountFixture;
import com.example.appkata.fixture.SessionFixture;
import com.example.appkata.module.account.application.dto.*;
import com.example.appkata.module.account.domain.AccountRepository;
import com.example.appkata.module.account.infra.EmailSender;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(OutputCaptureExtension.class)
class AccountApiIntegrationTest {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;
    @SpyBean
    EmailSender emailSender;

    @SpyBean
    AccountRepository accountRepository;

    @BeforeEach
    void setUp() {
        accountRepository.deleteAll();
    }


    @Test
    void 회원_등록_요청(CapturedOutput capturedOutput) throws Exception { //TODO - c-a-1-2: user register시, email 발송하는 log를 capture해서 테스트
        // given
        String username = AccountFixture.FIXTURE_USER_NAME;
        String email = AccountFixture.FIXTURE_USER_EMAIL;
        CreateAccountRequest request = new CreateAccountRequest(username, email);

        // when
        MockHttpServletResponse response = mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andReturn().getResponse();

        // then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        CreateAccountResponse createAccountResponse = objectMapper.readValue(response.getContentAsString(),
                CreateAccountResponse.class);
        Assertions.assertThat(createAccountResponse.getId()).isPositive();
        Assertions.assertThat(createAccountResponse.getName()).isEqualTo(username);
        Assertions.assertThat(createAccountResponse.getEmail()).isEqualTo(email);

        Thread.sleep(1000);

        verify(emailSender, times(1)).sendEmail(email, username); //TODO - c-a-1-1: user register시, email 발송 테스트
        String consoleEmailFormat = String.format("[EmailSender] 이메일을 보냅니다. email: %s, username: %s", email, username);
        Assertions.assertThat(capturedOutput.getOut()).contains(consoleEmailFormat); //TODO - c-a-1-2: user register시, email 발송하는 log를 capture해서 테스트
    }

    @Test
    @Transactional
    void 회원_수정_요청() throws Exception {
        // given
        MockHttpSession session = SessionFixture.getLoginSession(mockMvc, objectMapper);
        String expectedUsername = "doohwancho";
        UpdateAccountRequest request = new UpdateAccountRequest(expectedUsername);

        // when
        MockHttpServletResponse response = mockMvc.perform(patch("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .session(session)
        ).andReturn().getResponse();

        // then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        UpdateAccountResponse updateAccountResponse = objectMapper.readValue(response.getContentAsString(),
                UpdateAccountResponse.class);
        Assertions.assertThat(updateAccountResponse.getName()).isEqualTo(expectedUsername);
    }

    @Test
    void 회원_삭제_요청() throws Exception {
        // given

        // when
        MockHttpServletResponse response = mockMvc.perform(delete("/accounts")
                .session(SessionFixture.getLoginSession(mockMvc, objectMapper))
        ).andReturn().getResponse();
        // then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void 회원_조회_요청() throws Exception {
        // given

        // when
        MockHttpServletResponse response = mockMvc.perform(get("/accounts")
                        .session(SessionFixture.getLoginSession(mockMvc, objectMapper)))
                .andReturn().getResponse();

        // then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        FindAccountResponse findAccountResponse = objectMapper.readValue(response.getContentAsString(),
                FindAccountResponse.class);
        Assertions.assertThat(findAccountResponse.getUsername()).isEqualTo(AccountFixture.FIXTURE_USER_NAME);
        Assertions.assertThat(findAccountResponse.getEmail()).isEqualTo(AccountFixture.FIXTURE_USER_EMAIL);
    }

}