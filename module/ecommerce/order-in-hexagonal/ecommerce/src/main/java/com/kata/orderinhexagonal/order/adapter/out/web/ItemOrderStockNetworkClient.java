package com.kata.orderinhexagonal.order.adapter.out.web;

import com.kata.orderinhexagonal.item.domain.Item;

public interface ItemOrderStockNetworkClient {
    void stockOut(Item item, int orderQuantity);
}
