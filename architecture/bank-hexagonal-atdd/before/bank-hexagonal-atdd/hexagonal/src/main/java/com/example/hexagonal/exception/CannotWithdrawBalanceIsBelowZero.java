package com.example.hexagonal.exception;

public class CannotWithdrawBalanceIsBelowZero extends RuntimeException {
    public CannotWithdrawBalanceIsBelowZero(String message) {
        super(message);
    }
}
