package com.kata.orderinhexagonal.item.adapter.out.persistence;

import com.kata.orderinhexagonal.item.application.port.out.SaveItemPort;
import com.kata.orderinhexagonal.item.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateItemAdapter implements SaveItemPort {

    private final ItemRepository itemRepository;

    private final ItemMapper itemMapper;

    @Override
    public void saveItem(Item item) {
        item.initializeStockQuantity(0);
        ItemEntity itemEntity = itemMapper.toEntity(item);
        ItemEntity savedItem = itemRepository.save(itemEntity);
        item.assignId(savedItem.getId());
    }
}
