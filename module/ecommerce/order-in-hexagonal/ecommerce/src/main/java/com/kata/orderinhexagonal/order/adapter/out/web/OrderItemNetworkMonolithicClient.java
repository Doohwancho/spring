package com.kata.orderinhexagonal.order.adapter.out.web;

import com.kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import com.kata.orderinhexagonal.item.adapter.out.persistence.ItemMapper;
import com.kata.orderinhexagonal.item.adapter.out.persistence.ItemRepository;
import com.kata.orderinhexagonal.item.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemNetworkMonolithicClient implements OrderItemNetworkClient {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public Item load(Long itemId) {
        ItemEntity itemEntity = itemRepository.findItemAndDiscountFetchById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
        return itemMapper.toDomain(itemEntity);
    }
}
