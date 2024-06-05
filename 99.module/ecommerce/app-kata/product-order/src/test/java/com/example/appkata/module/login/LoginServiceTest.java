package com.example.appkata.module.login;

import com.example.appkata.module.login.application.dto.LoginRequest;
import com.example.appkata.module.account.domain.Account;
import com.example.appkata.module.account.domain.AccountRepository;
import com.example.appkata.module.login.application.LoginService;
import com.example.appkata.module.login.application.dto.LoginSession;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginServiceTest {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    LoginService loginService;

    @BeforeEach
    void setUp() {
        accountRepository.deleteAll();
    }


    @Test
    void 로그인() {
        // given
        String email = "doohwancho@gmail.com";
        String expectedUsername = "doohwancho";
        LoginRequest request = new LoginRequest(email);
        accountRepository.save(new Account(expectedUsername, email));

        // when
        LoginSession loginSession = loginService.login(request);

        // then
        Assertions.assertThat(loginSession.getEmail()).isEqualTo(email);
        Assertions.assertThat(loginSession.getUsername()).isEqualTo(expectedUsername);
    }
    @Test
    void 로그인_실패_없는_이메일() {
        // given
        String email = "doohwancho@gmail.com";
        String expectedUsername = "doohwancho";
        accountRepository.save(new Account(expectedUsername, "email@gmail.com"));
        LoginRequest request = new LoginRequest(email);

        // when
        Assertions.assertThatThrownBy(() -> loginService.login(request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Account not found");
    }

}
