package com.example.appkata.module.product.domain;

import lombok.Getter;

@Getter
public class Product {
    private Long id;
    private String name;
    private int price;

    public Product(String productName, int price) {
        this.name = productName;
        this.price = price;
    }

    public void assignId(long nextId) {
        this.id = nextId;
    }

    public void update(String productName, int price) {
        this.name = productName;
        this.price = price;
    }
}
