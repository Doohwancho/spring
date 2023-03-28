package com.example.appkata.module.acount;

import com.example.appkata.module.account.application.dto.AccountExceptionResponse;
import com.example.appkata.module.account.application.dto.CreateAccountRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.io.UnsupportedEncodingException;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
class AccountApiTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("유효하지 않은 이메일 주소를 입력하면 Bad Request 응답.")
    void should_invalid_email_join_account_return_bad_request() throws Exception {
        // given
        String username = "doohwancho";
        badRequestAssertions("올바른 이메일 형식이 아닙니다.",
                joinAccountRequest(new CreateAccountRequest(username, "doohwancho")));

        badRequestAssertions("올바른 이메일 형식이 아닙니다.",
                joinAccountRequest(new CreateAccountRequest(username, "doohwancho@@gmail.com")));

        badRequestAssertions("올바른 이메일 형식이 아닙니다.",
                joinAccountRequest(new CreateAccountRequest(username, "doohwancho.gmail.com")));

        badRequestAssertions("이메일을 입력해주세요.",
                joinAccountRequest(new CreateAccountRequest(username, "")));

        badRequestAssertions("이름을 입력해주세요.",
                joinAccountRequest(new CreateAccountRequest("", "doohwancho@gmail.com")));
    }

    private MockHttpServletResponse joinAccountRequest(CreateAccountRequest request) throws Exception {
        return mockMvc.perform(post("/accounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andReturn().getResponse();
    }

    private void badRequestAssertions(String exceptionMessage, MockHttpServletResponse response)
            throws JsonProcessingException, UnsupportedEncodingException {
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        AccountExceptionResponse exceptionResponse = objectMapper.readValue(response.getContentAsString(),
                AccountExceptionResponse.class);
        Assertions.assertThat(exceptionResponse.getMessage()).contains(exceptionMessage);
    }

}
