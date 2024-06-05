package com.kata.orderinhexagonal.order.adapter.out.web;

import com.kata.orderinhexagonal.item.domain.Item;
import com.kata.orderinhexagonal.order.application.port.out.CancelStockOutItemPort;
import com.kata.orderinhexagonal.order.application.port.out.ItemStockOutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemOrderStockAdapter implements ItemStockOutPort, CancelStockOutItemPort {
    private final ItemOrderStockNetworkClient itemOrderStockNetworkClient;

    @Override
    public void stockOut(Item item, int orderQuantity) {
        itemOrderStockNetworkClient.stockOut(item, orderQuantity);
    }

    @Override
    public void cancelStockOutItem(Item item, int orderQuantity) {
        itemOrderStockNetworkClient.stockIn(item, orderQuantity);
    }
}
