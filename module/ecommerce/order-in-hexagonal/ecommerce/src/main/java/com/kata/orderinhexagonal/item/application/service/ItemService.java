package com.kata.orderinhexagonal.item.application.service;


import com.kata.orderinhexagonal.item.application.port.in.CreateItemUsecase;
import com.kata.orderinhexagonal.item.application.port.out.SaveItemPort;
import com.kata.orderinhexagonal.item.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemService implements CreateItemUsecase {
    private final SaveItemPort saveItemPort;

    public Item createItem(com.kata.orderinhexagonal.item.application.port.in.CreateItemRequest request) {
        Item item = Item.builder()
                .name(request.getName())
                .price(request.getPrice())
                .build();

        saveItemPort.saveItem(item);

        return item;
    }
}
