package com.kata.orderinhexagonal.order.adapter.out.web;

import com.kata.orderinhexagonal.item.domain.Item;
import com.kata.orderinhexagonal.order.application.port.out.ItemStockOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemOrderStockAdapter implements ItemStockOutPort {
    private ItemOrderStockNetworkClient itemOrderStockNetworkClient;

    @Override
    public void stockOut(Item item, int orderQuantity) {
        itemOrderStockNetworkClient.stockOut(item, orderQuantity);
    }
}
