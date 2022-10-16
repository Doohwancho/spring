package com.cheese.swagger.account.exception;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(long id) {
        super(id + " is not exited");
    }
}
