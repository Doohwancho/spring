package com.kata.orderinhexagonal.discount.application.port.out;

import com.kata.orderinhexagonal.item.domain.Item;

import java.util.Optional;

public interface DiscountItemLoadPort {
    Item loadItem(Long itemId);
}
