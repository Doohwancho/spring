package com.example.cho.email_verification.service;

import com.example.cho.email_verification.domain.VerificationCode;
import com.example.cho.email_verification.exception.CodeAlreadyExistsException;
import com.example.cho.email_verification.exception.MaxAttemptsExceededException;
import com.example.cho.email_verification.exception.TooManyRequestsException;
import com.example.cho.email_verification.exception.VerificationException;
import com.google.common.util.concurrent.RateLimiter;
import org.apache.commons.validator.routines.EmailValidator;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailVerificationService {
    
    private final JavaMailSender emailSender;
    private final ConcurrentHashMap<String, VerificationCode> verificationCodes = new ConcurrentHashMap<>();
    private final SecureRandom secureRandom = new SecureRandom();
    private final RateLimiter rateLimiter = RateLimiter.create(1.0); // 1 request per second
    
    private static final int VERIFICATION_CODE_LENGTH = 6;
    private static final int VERIFICATION_CODE_EXPIRY_MINUTES = 5;
    private static final int MAX_VERIFICATION_ATTEMPTS = 3;
    private static final String CODE_CHARS = "0123456789";
    
    @Value("${spring.mail.username}")
    private String fromEmail;
    
    /*
        # Send verification code
        POST /api/verification/send
        {
            "email": "user@example.com"
        }
     */
    public void sendVerificationCode(String toEmail) {
        //email validation
        if (!EmailValidator.getInstance().isValid(toEmail)) {
            log.error("Invalid email address: {}", toEmail);
            throw new IllegalArgumentException("Invalid email address");
        }
        
        //rate-limit
        if (!rateLimiter.tryAcquire()) {
            log.warn("Rate limit exceeded for email: {}", toEmail);
            throw new TooManyRequestsException("Please wait before requesting another code");
        }
        
        try {
            // sending code
            String code = generateVerificationCode();
            LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(VERIFICATION_CODE_EXPIRY_MINUTES);
    
            // Store or update verification code
            verificationCodes.compute(toEmail, (key, existingCode) -> {
                if (existingCode != null && LocalDateTime.now().isBefore(existingCode.getExpiryTime())) {
                    log.warn("Existing valid code found for email: {}", toEmail);
                    throw new CodeAlreadyExistsException("Valid verification code already exists");
                }
                return new VerificationCode(code, expiryTime, 0);
            });
    
            sendEmail(toEmail, code);
            
            //logging
//            log.info("Verification code sent successfully to {}", toEmail);
        } catch (Exception e) {
            log.error("Failed to send verification code to {}: {}", toEmail, e.getMessage());
            throw new VerificationException("Failed to process verification request", e);
        }
    }
    
    /*
        # Verify code
        POST /api/verification/verify
        {
            "email": "user@example.com",
            "code": "123456"
        }
     */
    public boolean verifyCode(String email, String code) {
//        log.info("Attempting to verify code for email: {}", email);
        
        VerificationCode storedCode = verificationCodes.get(email);
        
        if (storedCode == null) {
            log.warn("No verification code found for email: {}", email);
            return false;
        }
        
        // Check expiry
        if (LocalDateTime.now().isAfter(storedCode.getExpiryTime())) {
            log.warn("Verification code expired for email: {}", email);
            verificationCodes.remove(email);
            return false;
        }
        
        // Check attempts
        if (storedCode.getAttempts() >= MAX_VERIFICATION_ATTEMPTS) {
            log.warn("Max verification attempts exceeded for email: {}", email);
            verificationCodes.remove(email);
            throw new MaxAttemptsExceededException("Maximum verification attempts exceeded");
        }
        
        // Update attempts
        storedCode.incrementAttempts();
        
        // Verify code
        boolean isValid = storedCode.getCode().equals(code);
        if (isValid) {
//            log.info("Code verified successfully for email: {}", email);
            verificationCodes.remove(email);
        } else {
            log.warn("Invalid code attempt for email: {}", email);
        }
        
        return isValid;
    }
    
    private void sendEmail(String toEmail, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject("Your Verification Code");
        message.setText(String.format(
            "Your verification code is: %s\n" +
                "This code will expire in %d minutes.\n" +
                "If you didn't request this code, please ignore this email.",
            code, VERIFICATION_CODE_EXPIRY_MINUTES));
        
        emailSender.send(message);
    }
    
    private String generateVerificationCode() {
        StringBuilder code = new StringBuilder(VERIFICATION_CODE_LENGTH);
        for (int i = 0; i < VERIFICATION_CODE_LENGTH; i++) {
            code.append(CODE_CHARS.charAt(secureRandom.nextInt(CODE_CHARS.length())));
        }
        return code.toString();
    }
    
    @Scheduled(fixedRate = 3600000) // Every hour, remove expired code
    public void cleanupExpiredCodes() {
//        log.info("Starting cleanup of expired verification codes");
//        int initialSize = verificationCodes.size();
        
        verificationCodes.entrySet().removeIf(entry -> {
            boolean isExpired = LocalDateTime.now().isAfter(entry.getValue().getExpiryTime());
            if (isExpired) {
                log.debug("Removing expired code for email: {}", entry.getKey());
            }
            return isExpired;
        });
        
//        log.info("Cleanup completed. Removed {} expired codes", initialSize - verificationCodes.size());
    }
}