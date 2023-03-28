package com.example.appkata.module.order.application.dto;

import com.example.appkata.module.order.domain.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class FindOrderResponse {
    private Long id;
    private Long productId;
    private String productName;
    private int totalPrice;
    private int quantity;

    public FindOrderResponse(Order order) {
        this.id = order.getId();
        this.productId = order.getProduct().getId();
        this.productName = order.getProduct().getName();
        this.totalPrice = order.getTotalPrice();
        this.quantity = order.getQuantity();
    }
}
