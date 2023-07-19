package com.example.hexagonal.bank.fixture;

import com.example.hexagonal.repository.AccountRepository;
import com.example.hexagonal.service.AccountService;
import com.example.hexagonal.vo.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountFixture {

    public static final long INITIAL_BALANCE = 10000L;

    @Autowired
    AccountService service;

    @Autowired
    AccountRepository repository;

    public Account 계정준비(){
        Account account = Account.builder()
                .ownerName("cho")
                .balance(INITIAL_BALANCE)
                .build();
        return repository.save(account);
    }
}
