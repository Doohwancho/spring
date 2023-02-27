package com.example.hexagonal.bank;

class AccountDoesNotExist extends RuntimeException {
    public AccountDoesNotExist(String message) {
        super(message);
    }
}
