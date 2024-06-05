package com.kata.orderinhexagonal.order.application.port.out;

import com.kata.orderinhexagonal.item.domain.Item;

public interface LoadOrderItemPort {
    Item load(Long itemId);
}
