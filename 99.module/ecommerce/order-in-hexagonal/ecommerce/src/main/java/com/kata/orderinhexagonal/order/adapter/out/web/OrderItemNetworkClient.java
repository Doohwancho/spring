package com.kata.orderinhexagonal.order.adapter.out.web;

import com.kata.orderinhexagonal.item.domain.Item;

public interface OrderItemNetworkClient {
    Item load(Long itemId);
}
