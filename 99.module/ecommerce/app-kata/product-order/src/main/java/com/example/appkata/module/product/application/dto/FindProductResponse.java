package com.example.appkata.module.product.application.dto;

import com.example.appkata.module.product.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class FindProductResponse {
    private long id;
    private String productName;
    private int price;

    public FindProductResponse(Product product) {
        this.id = product.getId();
        this.productName = product.getName();
        this.price = product.getPrice();
    }
}
