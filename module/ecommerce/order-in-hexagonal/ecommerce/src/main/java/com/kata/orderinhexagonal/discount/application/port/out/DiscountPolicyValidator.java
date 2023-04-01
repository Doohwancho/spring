package com.kata.orderinhexagonal.discount.application.port.out;

public interface DiscountPolicyValidator {
    boolean existsDiscountPolicy(Long itemId);
}
