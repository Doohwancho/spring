package com.kata.orderinhexagonal.stock.adapter.out.web;

import com.kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import com.kata.orderinhexagonal.item.domain.Item;

public interface StockItemNetworkClient {
    Item findItemById(Long id);

    ItemEntity findItemEntityById(Long id);
}
