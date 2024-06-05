package com.example.appkata.module.login.application;

import com.example.appkata.module.account.application.AccountSessionManager;
import com.example.appkata.module.account.domain.Account;
import com.example.appkata.module.account.domain.AccountRepository;
import com.example.appkata.module.login.application.dto.LoginRequest;
import com.example.appkata.module.login.application.dto.LoginSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final AccountRepository accountRepository;
    private final AccountSessionManager sessionManager;
    public LoginSession login(LoginRequest request) {
        Account account = accountRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        LoginSession loginSession = new LoginSession(account.getEmail(), account.getUsername());
        sessionManager.saveLoginUserId(account.getId());
        return loginSession;
    }
}
