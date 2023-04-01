package com.kata.orderinhexagonal.discount.application.port.out;

import com.kata.orderinhexagonal.discount.domain.Discount;

public interface CreateDiscountPort {
    void save(Discount discount);
}
