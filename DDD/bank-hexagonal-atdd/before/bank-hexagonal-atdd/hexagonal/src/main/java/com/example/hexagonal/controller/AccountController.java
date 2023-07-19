package com.example.hexagonal.controller;

import com.example.hexagonal.dto.deposit.DepositRequest;
import com.example.hexagonal.dto.deposit.DepositResponse;
import com.example.hexagonal.dto.withdraw.WithdrawRequest;
import com.example.hexagonal.dto.withdraw.WithdrawResponse;
import com.example.hexagonal.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @PostMapping(value = "/deposit")
    public DepositResponse deposit(@RequestBody DepositRequest request) {
        return service.deposit(request);
    }

    @PostMapping(value = "/withdraw")
    public WithdrawResponse withdraw(@RequestBody WithdrawRequest request) {
        return service.withdraw(request);
    }
}
