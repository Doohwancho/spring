package com.example.appkata.module.order.domain;

import com.example.appkata.module.product.domain.Product;
import lombok.Getter;

@Getter
public class Order {
    private Long id;
    private Product product;
    private int quantity;

    public Order(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return product.getPrice() * quantity;
    }

    public void assignId(long nextId) {
        this.id = nextId;
    }
}
