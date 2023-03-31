package com.kata.orderinhexagonal.stock.application.port.in;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockOutResponse {
    private long id;
    private long itemId;
    private int quantity;
    private String itemName;

    public StockOutResponse(Long id, Long itemId, Integer quantity, String itemName) {
        this.id = id;
        this.itemId = itemId;
        this.quantity = quantity;
        this.itemName = itemName;
    }
}