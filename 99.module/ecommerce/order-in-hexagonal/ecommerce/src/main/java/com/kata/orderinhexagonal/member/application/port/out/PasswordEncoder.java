package com.kata.orderinhexagonal.member.application.port.out;

public interface PasswordEncoder {
    String encode(String password);
}