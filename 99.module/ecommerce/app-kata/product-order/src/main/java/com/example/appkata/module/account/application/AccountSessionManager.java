package com.example.appkata.module.account.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
@RequiredArgsConstructor
public class AccountSessionManager {
    private final HttpSession session;
    public static final String LOGIN_USER_KEY = "LOGIN_USER";

    public void saveLoginUserId(Long accountId) {
        session.setAttribute(LOGIN_USER_KEY, accountId);
    }
    public Long getLoginUserId() {
        return (Long)session.getAttribute(LOGIN_USER_KEY);
    }
}
