package com.kata.orderinhexagonal.delivery.adapter.out.web;

import com.kata.orderinhexagonal.delivery.application.port.out.OrderDeliveryLoadPort;
import com.kata.orderinhexagonal.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderDeliveryLoadAdapter implements OrderDeliveryLoadPort {


    private final OrderDeliveryNetworkClient orderDeliveryNetworkClient;

    @Override
    public Order load(Long orderId) {
        return orderDeliveryNetworkClient.loadOrder(orderId);
    }
}
