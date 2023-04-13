package com.kata.orderinhexagonal.payment.adapter.out.web;

import com.kata.orderinhexagonal.order.domain.Order;

public interface PaymentOrderNetworkClient {
    Order findOrder(Long orderId);
}
