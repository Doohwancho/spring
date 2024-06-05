package com.kata.orderinhexagonal.order.application.port.in;

import com.kata.orderinhexagonal.order.domain.Order;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CancelOrderResponse {
    private Long orderId;

    public CancelOrderResponse(Order cancelOrder) {
        this.orderId = cancelOrder.getId();
    }
}
