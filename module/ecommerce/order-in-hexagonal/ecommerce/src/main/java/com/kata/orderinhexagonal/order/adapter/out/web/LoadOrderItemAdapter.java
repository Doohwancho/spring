package com.kata.orderinhexagonal.order.adapter.out.web;

import com.kata.orderinhexagonal.item.domain.Item;
import com.kata.orderinhexagonal.order.application.port.out.LoadOrderItemPort;
import com.kata.orderinhexagonal.order.application.port.out.LoadOrdererPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoadOrderItemAdapter implements LoadOrderItemPort {
    private final OrderItemNetworkClient orderItemNetworkClient;

    @Override
    public Item load(Long itemId) {
        return orderItemNetworkClient.load(itemId);
    }
}
