package com.example.cho;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.cho.email_verification.service.EmailVerificationService;
import com.example.cho.email_verification.exception.TooManyRequestsException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
class EmailVerificationServiceTest {
    @MockBean
    private JavaMailSender emailSender;
    
    @Autowired
    private EmailVerificationService verificationService;
    
    @Test
    void shouldThrottleRequests() {
        String email = "doohwancho1993@gmail.com";
        
        // First request should succeed
        verificationService.sendVerificationCode(email);
        
        // Second immediate request should be throttled
        assertThrows(TooManyRequestsException.class, () ->
            verificationService.sendVerificationCode(email));
    }
}
