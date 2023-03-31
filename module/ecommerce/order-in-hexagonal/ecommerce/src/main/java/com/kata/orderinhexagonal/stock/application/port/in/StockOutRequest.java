package com.kata.orderinhexagonal.stock.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StockOutRequest {
    private Long id;
    private int quantity;

    public static StockOutRequest of(Long id, int quantity) {
        return new StockOutRequest(id, quantity);
    }
}
