package com.kata.orderinhexagonal.stock.adapter.out.web;

import com.kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import com.kata.orderinhexagonal.item.adapter.out.persistence.ItemMapper;
import com.kata.orderinhexagonal.item.adapter.out.persistence.ItemRepository;
import com.kata.orderinhexagonal.item.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StockItemNetworkMonolithicClient implements StockItemNetworkClient {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public Item findItemById(Long id) {
        ItemEntity itemEntity = findItemEntityById(id);
        return itemMapper.toDomain(itemEntity);
    }

    @Override
    public ItemEntity findItemEntityById(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
    }
}
