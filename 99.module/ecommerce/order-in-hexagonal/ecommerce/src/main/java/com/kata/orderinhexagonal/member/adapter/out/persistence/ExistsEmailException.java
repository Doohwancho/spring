package com.kata.orderinhexagonal.member.adapter.out.persistence;

public class ExistsEmailException extends RuntimeException {
    public ExistsEmailException(String msg) {
        super(msg);
    }
}
