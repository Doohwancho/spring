package com.kata.orderinhexagonal.item.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class Item {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;

    public void assignId(long id) {
        this.id = id;
    }

    public void initializeStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void stockInQuantity(Integer stockInQuantity) {
        this.stockQuantity += stockInQuantity;
    }
    public void stockOutQuantity(Integer stockOutQuantity) {
        verifyStockOutQuantity(stockOutQuantity);
        this.stockQuantity -= stockOutQuantity;
    }

    private void verifyStockOutQuantity(Integer stockOutQuantity) {
        if((this.stockQuantity - stockOutQuantity) < 0) {
            throw new RuntimeException("Stock Out quantity is greater than stock quantity");
        }
    }
}
