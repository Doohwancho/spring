package com.kata.orderinhexagonal.discount.application.port.in;

import com.kata.orderinhexagonal.discount.domain.Discount;

public interface CreateItemDiscountUsecase {
    Discount applyDiscount(ItemDiscountRequest request);
}
