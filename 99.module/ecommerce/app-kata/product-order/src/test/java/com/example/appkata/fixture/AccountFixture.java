package com.example.appkata.fixture;

import com.example.appkata.module.account.application.AccountService;
import com.example.appkata.module.account.application.dto.CreateAccountRequest;
import com.example.appkata.module.account.domain.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountFixture {
    private final AccountService accountService;
    public static final String FIXTURE_USER_NAME = "doohwancho";
    public static final String FIXTURE_USER_EMAIL = "doohwancho@gmail.com";

    public AccountFixture(AccountService accountService) {
        this.accountService = accountService;
    }

    public Account createAccount() {
        return accountService.join(new CreateAccountRequest(FIXTURE_USER_NAME, FIXTURE_USER_EMAIL));
    }
}
