package com.example.appkata.module.product.application.dto;


import com.example.appkata.module.product.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class UpdateProductResponse {
    private Long id;
    private String productName;
    private int price;

    public UpdateProductResponse(Product updateProduct) {
        this.id = updateProduct.getId();
        this.productName = updateProduct.getName();
        this.price = updateProduct.getPrice();
    }
}
