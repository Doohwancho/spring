package com.kata.orderinhexagonal.order.application.port.out;

import com.kata.orderinhexagonal.order.domain.Order;

public interface OrderStatusChangePort {
    void changeStatus(Order order);
}
