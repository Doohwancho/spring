package com.example.cho.email_verification.dto;

import lombok.Data;

@Data
public class VerificationRequest {
    private String email;
    private String code;
}