package com.kata.orderinhexagonal.order.adapter.out.web;

public interface OrderPaymentNetworkClient {
    void cancelPaymentRequest(Long orderId);
}

