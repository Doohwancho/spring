package com.example.hexagonal.bank;

public class CannotWithdrawBalanceIsBelowZero extends RuntimeException {
    public CannotWithdrawBalanceIsBelowZero(String message) {
        super(message);
    }
}
