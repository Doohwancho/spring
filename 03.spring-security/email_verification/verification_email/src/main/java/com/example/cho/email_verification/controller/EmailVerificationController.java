package com.example.cho.email_verification.controller;

import com.example.cho.email_verification.service.EmailVerificationService;
import com.example.cho.email_verification.dto.EmailRequest;
import com.example.cho.email_verification.dto.VerificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/verification")
@RequiredArgsConstructor
public class EmailVerificationController {
    
    private final EmailVerificationService verificationService;
    
    @PostMapping("/send")
    public ResponseEntity<String> sendVerificationCode(@RequestBody EmailRequest request) {
        try {
            verificationService.sendVerificationCode(request.getEmail());
            return ResponseEntity.ok("Verification code sent successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to send verification code: " + e.getMessage());
        }
    }
    
    @PostMapping("/verify")
    public ResponseEntity<String> verifyCode(@RequestBody VerificationRequest request) {
        boolean isValid = verificationService.verifyCode(request.getEmail(), request.getCode());
        if (isValid) {
            return ResponseEntity.ok("Email verified successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired verification code");
        }
    }
}
