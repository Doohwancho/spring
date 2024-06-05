package com.kata.orderinhexagonal.payment.adapter.out.web;

import com.kata.orderinhexagonal.order.domain.Order;
import com.kata.orderinhexagonal.payment.application.port.out.OrderLoadPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderLoadAdapter implements OrderLoadPort {

    private final PaymentOrderNetworkClient paymentOrderNetworkClient;

    @Override
    public Order load(Long orderId) {
        return paymentOrderNetworkClient.findOrder(orderId);
    }
}
