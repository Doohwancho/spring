package com.kata.orderinhexagonal.payment.adapter.out.web;

import com.kata.orderinhexagonal.order.adapter.out.persistence.FindOrderAdapter;
import com.kata.orderinhexagonal.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentOrderNetworkMonolithicClient implements PaymentOrderNetworkClient {

    private final FindOrderAdapter findOrderAdapter;
    @Override
    public Order findOrder(Long orderId) {
        return findOrderAdapter.loadOrder(orderId);
    }
}