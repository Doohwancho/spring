package com.kata.orderinhexagonal.stock.application.port.out;

import com.kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import com.kata.orderinhexagonal.item.domain.Item;

public interface LoadItemPort {
    Item load(Long id);

    ItemEntity loadItemEntity(Long id);
}
