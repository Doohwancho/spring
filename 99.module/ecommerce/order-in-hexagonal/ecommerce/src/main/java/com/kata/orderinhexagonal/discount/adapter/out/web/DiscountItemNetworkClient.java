package com.kata.orderinhexagonal.discount.adapter.out.web;

import com.kata.orderinhexagonal.item.domain.Item;

import java.util.Optional;

public interface DiscountItemNetworkClient {
    Item loadItem(Long itemId);
}
