package com.example.appkata.module.login.application.dto;

import lombok.Getter;

@Getter
public class LoginSession {
    private final String email;
    private final String username;

    public LoginSession(String email, String username) {
        this.email = email;
        this.username = username;
    }
}