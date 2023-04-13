package com.kata.orderinhexagonal.payment.application.port.out;

import com.kata.orderinhexagonal.order.domain.Order;

public interface OrderLoadPort {
    Order load(Long orderId);
}
