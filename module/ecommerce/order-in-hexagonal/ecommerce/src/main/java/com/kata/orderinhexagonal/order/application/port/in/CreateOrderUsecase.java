package com.kata.orderinhexagonal.order.application.port.in;

import com.kata.orderinhexagonal.order.domain.Order;

public interface CreateOrderUsecase {
    Order createOrder(CreateOrderRequest request);
}
