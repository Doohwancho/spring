package com.kata.orderinhexagonal.stock.adapter.out.web;

import com.kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import com.kata.orderinhexagonal.item.domain.Item;
import com.kata.orderinhexagonal.stock.application.port.out.LoadItemPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class LoadItemAdapter implements LoadItemPort {

    private final StockItemNetworkClient stockItemNetworkClient;

    @Override
    public Item load(Long id) {
        return stockItemNetworkClient.findItemById(id);
    }

    @Override
    public ItemEntity loadItemEntity(Long id) {
        return stockItemNetworkClient.findItemEntityById(id);
    }


}
