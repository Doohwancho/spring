package com.example.cho.email_verification.domain;

import java.time.LocalDateTime;
import lombok.Value;

@Value
public class VerificationCode {
    String code;
    LocalDateTime expiryTime;
    int attempts;
    
    public int incrementAttempts() {
        return attempts + 1;
    }
}
