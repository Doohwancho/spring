package com.kata.orderinhexagonal.order.application.port.out;

import com.kata.orderinhexagonal.order.adapter.out.persistence.OrderEntity;
import com.kata.orderinhexagonal.order.domain.Order;

public interface LoadOrderPort {
    Order loadOrder(Long orderId);

    OrderEntity loadOrderEntity(Long orderId);
}
