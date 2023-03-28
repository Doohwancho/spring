package com.example.appkata.module.account.infra;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class ConsoleEmailSender implements EmailSender {
    @Override
    public void sendEmail(String email, String username) {
        log.info("[EmailSender] 이메일을 보냅니다. email: {}, username: {}", email, username);
    }
}
