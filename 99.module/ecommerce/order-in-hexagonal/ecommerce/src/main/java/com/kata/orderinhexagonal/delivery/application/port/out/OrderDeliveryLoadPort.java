package com.kata.orderinhexagonal.delivery.application.port.out;

import com.kata.orderinhexagonal.order.domain.Order;

public interface OrderDeliveryLoadPort {
    Order load(Long orderId);
}
