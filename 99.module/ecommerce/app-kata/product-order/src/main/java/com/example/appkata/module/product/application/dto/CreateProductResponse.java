package com.example.appkata.module.product.application.dto;

import com.example.appkata.module.product.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class CreateProductResponse {
    private long id;
    private String productName;
    private int price;

    public CreateProductResponse(String productName, int price) {
        this.productName = productName;
        this.price = price;
    }

    public CreateProductResponse(Product product) {
        this.id = product.getId();
        this.productName = product.getName();
        this.price = product.getPrice();
    }
}
