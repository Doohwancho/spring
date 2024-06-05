package com.kata.orderinhexagonal.order.adapter.out.web;

import lombok.Getter;

@Getter
public class CancellationPaymentRequestEvent {

    private final Long orderId;
    public CancellationPaymentRequestEvent(Long orderId) {
        this.orderId = orderId;
    }
}