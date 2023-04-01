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
    private Integer price;
    private Integer stockQuantity;

    public void assignId(long id) {
        this.id = id;
    }

    public void initializeStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void stockInQuantity(Integer stockInQuantity) {
        verifyStockQuantityIsInitialized();

        this.stockQuantity += stockInQuantity;
    }

    private void verifyStockQuantityIsInitialized() {
        if(this.stockQuantity == null) {
            throw new RuntimeException("Stock quantity is not initialized");
        }
    }

    public void stockOutQuantity(Integer stockOutQuantity) {
        verifyStockQuantityIsInitialized();
        verifyStockOutQuantity(stockOutQuantity);
        this.stockQuantity -= stockOutQuantity;
    }

    private void verifyStockOutQuantity(Integer stockOutQuantity) {
        if((this.stockQuantity - stockOutQuantity) < 0) {
            throw new RuntimeException("Stock Out quantity is greater than stock quantity");
        }
    }
}
