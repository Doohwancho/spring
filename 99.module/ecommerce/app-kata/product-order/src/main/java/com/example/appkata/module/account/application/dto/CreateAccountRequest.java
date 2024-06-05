package com.example.appkata.module.account.application.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateAccountRequest {
    @NotBlank(message = "이름을 입력해주세요.")
    private String username;

    @Email(message = "올바른 이메일 형식이 아닙니다.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    public CreateAccountRequest(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
