package com.example.appkata.module.account.api;

import com.example.appkata.module.account.application.*;
import com.example.appkata.module.account.application.dto.*;
import com.example.appkata.module.account.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountApi {

    private final AccountService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateAccountResponse createAccount(@RequestBody @Valid CreateAccountRequest request) {
        Account newAccount = service.join(request);
        return CreateAccountResponse.of(newAccount);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdateAccountResponse updateAccount(@RequestBody UpdateAccountRequest request) {
        Account account = service.updateUsername(request);
        return new UpdateAccountResponse(account.getUsername());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccount() {
        service.removeUser();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public FindAccountResponse getAccount() {
        Account account = service.getUser();
        return new FindAccountResponse(account.getUsername(), account.getEmail());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public AccountExceptionResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new AccountExceptionResponse(e.getMessage());
    }
}
