package com.kata.orderinhexagonal.order.application.port.in;

import com.kata.orderinhexagonal.order.domain.Order;

public interface CancelOrderUsecase {
    Order cancelOrder(CancelOrderRequest request);
}
