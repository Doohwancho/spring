package com.kata.orderinhexagonal.order.application.port.in;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemResponse {
    private long itemId;
    private int quantity;
    private int price;

    public OrderItemResponse(Long id, int orderPrice, int orderQuantity) {
        this.itemId = id;
        this.quantity = orderQuantity;
        this.price = orderPrice;
    }
}
