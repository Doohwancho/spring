package com.example.appkata.module.account.infra;

import lombok.Getter;

@Getter
public class CreatedAccountEmailSendEvent {
    private final String email;
    private final String username;
    public CreatedAccountEmailSendEvent(String email, String username) {
        this.email = email;
        this.username = username;
    }
}
