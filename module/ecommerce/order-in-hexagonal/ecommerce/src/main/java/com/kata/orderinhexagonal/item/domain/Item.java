package com.kata.orderinhexagonal.item.domain;

import com.kata.orderinhexagonal.discount.domain.Discount;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Item {
    private Long id;
    private String name;
    private Integer price;
    private Integer stockQuantity;

    private Discount discount; //TODO - c-b-6-1. 원래 discount는 item에 속하지 않지만, repository에서 item을 가져올 때 discount를 가져오기 위해 임시로 있는 필드.

    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Item(long id, String name, int price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

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
