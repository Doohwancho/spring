package com.kata.orderinhexagonal.order.application.port.out;

import com.kata.orderinhexagonal.order.domain.Order;

public interface CancelPaymentPort {
    void request(Order order);
}
