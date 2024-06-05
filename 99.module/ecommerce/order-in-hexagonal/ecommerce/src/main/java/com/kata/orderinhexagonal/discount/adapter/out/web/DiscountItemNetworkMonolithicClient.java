package com.kata.orderinhexagonal.discount.adapter.out.web;

import com.kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import com.kata.orderinhexagonal.item.adapter.out.persistence.ItemMapper;
import com.kata.orderinhexagonal.item.adapter.out.persistence.ItemRepository;
import com.kata.orderinhexagonal.item.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DiscountItemNetworkMonolithicClient implements DiscountItemNetworkClient {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public Item loadItem(Long itemId) {

        ItemEntity itemEntity = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
        return itemMapper.toDomain(itemEntity);
    }
}
