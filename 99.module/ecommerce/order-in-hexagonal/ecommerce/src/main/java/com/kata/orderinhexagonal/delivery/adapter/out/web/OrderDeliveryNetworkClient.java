package com.kata.orderinhexagonal.delivery.adapter.out.web;

import com.kata.orderinhexagonal.order.domain.Order;

public interface OrderDeliveryNetworkClient {
    Order loadOrder(Long orderId);
}
