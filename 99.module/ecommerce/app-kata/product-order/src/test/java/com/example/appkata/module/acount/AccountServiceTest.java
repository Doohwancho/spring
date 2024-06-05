package com.example.appkata.module.acount;

import com.example.appkata.fixture.SessionFixture;
import com.example.appkata.module.account.application.AccountService;
import com.example.appkata.module.account.application.dto.CreateAccountRequest;
import com.example.appkata.module.account.application.dto.UpdateAccountRequest;
import com.example.appkata.module.account.domain.Account;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    SessionFixture sessionFixture;

    @Test
    @DisplayName("사용자 정보 등록")
    void join_account_test() {
        // given
        String username = "doohwancho";
        String email = "edoohwancho@gamil.com";
        CreateAccountRequest request = new CreateAccountRequest(username, email);

        // when
        Account account = accountService.join(request);

        // then
        Assertions.assertThat(account.getId()).isPositive();
        Assertions.assertThat(account.getUsername()).isEqualTo(username);
        Assertions.assertThat(account.getEmail()).isEqualTo(email);
    }

    @Test
    @DisplayName("사용자 이름 수정")
    void update_username_test() {
        // given
        sessionFixture.createSessionUser();
        String updateUsername = "doohwancho";
        UpdateAccountRequest request = new UpdateAccountRequest(updateUsername);
        // when
        Account updateAccount = accountService.updateUsername(request);

        // then
        Assertions.assertThat(updateAccount.getUsername()).isEqualTo(updateUsername);
    }

    @Test
    @DisplayName("사용자 삭제")
    void remove_user_test() {
        // given
        sessionFixture.createSessionUser();
        // when
        accountService.removeUser();

        // then
        Assertions.assertThatThrownBy(() -> {
            accountService.getUser();
        }).isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("Account not found");
    }
}
