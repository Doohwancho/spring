package com.example.appkata.module.product.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class UpdateProductRequest {

    private Long id;
    private String productName;
    private int price;

    public UpdateProductRequest(Long productId, String productName, int price) {
        this.id = productId;
        this.productName = productName;
        this.price = price;
    }

    public static UpdateProductRequest of(Long productId, String newProductName, int newPrice) {
        return new UpdateProductRequest(productId, newProductName, newPrice);
    }
}
