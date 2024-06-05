package com.example.appkata.module.order.application.dto;

import com.example.appkata.module.order.domain.Order;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class CreateOrderResponse {
    private long orderId;
    private long productId;
    private int quantity;
    private int totalPrice;
    private String productName;

    public CreateOrderResponse(Order order) {
        this.orderId = order.getId();
        this.productId = order.getProduct().getId();
        this.quantity = order.getQuantity();
        this.totalPrice = order.getTotalPrice();
        this.productName = order.getProduct().getName();
    }
}
