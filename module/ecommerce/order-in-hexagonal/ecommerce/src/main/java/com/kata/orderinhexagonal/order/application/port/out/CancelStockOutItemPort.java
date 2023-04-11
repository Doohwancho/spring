package com.kata.orderinhexagonal.order.application.port.out;

import com.kata.orderinhexagonal.item.domain.Item;

public interface CancelStockOutItemPort {
    void cancelStockOutItem(Item item, int orderQuantity);
}
