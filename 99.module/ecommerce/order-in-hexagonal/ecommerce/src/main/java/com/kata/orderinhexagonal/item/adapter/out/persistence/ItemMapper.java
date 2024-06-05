package com.kata.orderinhexagonal.item.adapter.out.persistence;

import com.kata.orderinhexagonal.item.domain.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public ItemEntity toEntity(Item domain) {
        return new ItemEntity(domain.getId(), domain.getName(), domain.getPrice(), domain.getStockQuantity());
    }

    public ItemEntity toEntity(String name, int price, int stockQuantity) {
        return new ItemEntity(name, price, stockQuantity);
    }

    public Item toDomain(ItemEntity entity) {
        return new Item(entity.getId(), entity.getName(), entity.getPrice(), entity.getStockQuantity());
    }
}
