package com.practice.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginDto {
    private final String username;
    private final String password;
}
