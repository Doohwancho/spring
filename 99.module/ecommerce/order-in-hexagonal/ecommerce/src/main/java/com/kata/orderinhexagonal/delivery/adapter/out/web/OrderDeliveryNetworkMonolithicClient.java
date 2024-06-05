package com.kata.orderinhexagonal.delivery.adapter.out.web;

import com.kata.orderinhexagonal.order.adapter.out.persistence.FindOrderAdapter;
import com.kata.orderinhexagonal.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderDeliveryNetworkMonolithicClient implements OrderDeliveryNetworkClient {

    private final FindOrderAdapter findOrderAdapter;

    @Override
    public Order loadOrder(Long orderId) {
        return findOrderAdapter.loadOrder(orderId);
    }
}
