package com.kata.orderinhexagonal.item.application.port.out;

import com.kata.orderinhexagonal.item.domain.Item;

public interface SaveItemPort {
    void saveItem(Item item);
}
