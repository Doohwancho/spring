package com.example.cho.email_verification.exception;

public class VerificationException extends RuntimeException {
    public VerificationException(String message, Throwable cause) {
        super(message, cause);
    }
}