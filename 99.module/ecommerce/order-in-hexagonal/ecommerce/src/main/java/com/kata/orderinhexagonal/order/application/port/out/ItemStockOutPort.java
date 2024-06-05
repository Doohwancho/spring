package com.kata.orderinhexagonal.order.application.port.out;

import com.kata.orderinhexagonal.item.domain.Item;

public interface ItemStockOutPort {
    void stockOut(Item item, int orderQuantity);
}
