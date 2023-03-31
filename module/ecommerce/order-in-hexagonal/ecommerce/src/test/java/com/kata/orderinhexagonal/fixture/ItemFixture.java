package com.kata.orderinhexagonal.fixture;

import com.kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import com.kata.orderinhexagonal.item.adapter.out.persistence.ItemMapper;
import com.kata.orderinhexagonal.item.adapter.out.persistence.ItemRepository;
import com.kata.orderinhexagonal.item.application.port.in.CreateItemRequest;
import com.kata.orderinhexagonal.item.application.service.ItemService;
import com.kata.orderinhexagonal.item.domain.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemFixture {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemMapper itemMapper;

    public Item createItem(String name, int price) {
        return itemService.createItem(CreateItemRequest.of(name, price));
    }

    public Item getItem(Long id) {
        ItemEntity itemEntity = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        return itemMapper.toDomain(itemEntity);
    }
}
