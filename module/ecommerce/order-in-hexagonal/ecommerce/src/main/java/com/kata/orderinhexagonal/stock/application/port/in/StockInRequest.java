package com.kata.orderinhexagonal.stock.application.port.in;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StockInRequest {
    private Long id;
    private int quantity;

    public StockInRequest(Long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public static StockInRequest of(Long id, int quantity) {
        return new StockInRequest(id, quantity);
    }
}
