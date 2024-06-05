package com.kata.orderinhexagonal.item.application.port.in;

import com.kata.orderinhexagonal.item.domain.Item;

public interface CreateItemUsecase {
    Item createItem(CreateItemRequest item);
}
