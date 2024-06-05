package com.example.appkata.module.order.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class CreateOrderRequest {
    private long productId;
    private int quantity;

    public CreateOrderRequest(Long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public static CreateOrderRequest of(Long productId, int quantity) {
        return new CreateOrderRequest(productId, quantity);
    }
}