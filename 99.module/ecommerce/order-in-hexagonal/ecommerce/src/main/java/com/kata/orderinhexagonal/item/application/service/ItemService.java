package com.kata.orderinhexagonal.item.application.service;


import com.kata.orderinhexagonal.item.application.port.in.CreateItemUsecase;
import com.kata.orderinhexagonal.item.application.port.in.CreateItemRequest;
import com.kata.orderinhexagonal.item.application.port.out.SaveItemPort;
import com.kata.orderinhexagonal.item.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemService implements CreateItemUsecase {
    private final SaveItemPort saveItemPort;

    public Item createItem(CreateItemRequest request) {
        Item item = new Item(request.getName(), request.getPrice());

        saveItemPort.saveItem(item);

        return item;
    }
}
