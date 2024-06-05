package com.example.appkata.module.account.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class AccountExceptionResponse {
    private String message;

    public AccountExceptionResponse(String message) {
        this.message = message;
    }
}
