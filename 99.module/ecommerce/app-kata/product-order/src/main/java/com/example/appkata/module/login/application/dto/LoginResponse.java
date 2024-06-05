package com.example.appkata.module.login.application.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponse {
    private String email;
    private String username;

    public LoginResponse(String email, String username) {
        this.email = email;
        this.username = username;
    }
}