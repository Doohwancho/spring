package com.kata.orderinhexagonal.order.application.port.in;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CancelOrderRequest {
    private Long orderId;

    private long ordererId;

    public CancelOrderRequest(Long orderId) {
        this.orderId = orderId;
    }

    public static CancelOrderRequest of(Long orderId) {
        return new CancelOrderRequest(orderId);
    }

    public void setOrdererId(long ordererId) {
        this.ordererId = ordererId;
    }
}
