package com.example.appkata.module.account.application.dto;

import com.example.appkata.module.account.domain.Account;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateAccountResponse {
    private long id;
    private String name;
    private String email;

    public CreateAccountResponse(Long id, String username, String email) {
        this.id = id;
        this.name = username;
        this.email = email;
    }

    public static CreateAccountResponse of(Account newAccount) {
        return new CreateAccountResponse(newAccount.getId(), newAccount.getUsername(), newAccount.getEmail());
    }
}