package com.example.appkata.module.account.infra;

public interface EmailSender {
    void sendEmail(String email, String username);
}
