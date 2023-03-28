package com.example.appkata.module.account.application;

import com.example.appkata.module.account.application.dto.CreateAccountRequest;
import com.example.appkata.module.account.application.dto.UpdateAccountRequest;
import com.example.appkata.module.account.domain.Account;
import com.example.appkata.module.account.domain.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository repository;
    private final AccountSessionManager sessionManager;

    public Account join(CreateAccountRequest request) {
        Account account = new Account(request.getUsername(), request.getEmail());
        repository.save(account);
        return account;
    }

    public Account updateUsername(UpdateAccountRequest request) {
        Account user = getUser();
        user.updateUsername(request.getUsername());
        return new Account(user.getUsername(), user.getEmail());
    }

    public void removeUser() {
        Account user = getUser();
        repository.delete(user);
    }

    public Account getUser() {
        Long loginUserId = sessionManager.getLoginUserId();
        return repository.findById(loginUserId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
    }
}
