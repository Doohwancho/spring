package com.kata.orderinhexagonal.order.adapter.out.web;

import com.kata.orderinhexagonal.item.domain.Item;
import com.kata.orderinhexagonal.stock.application.port.in.StockOutRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ItemOrderStockNetworkMonolithicClient implements ItemOrderStockNetworkClient{

    private final ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void stockOut(Item item, int orderQuantity) {
        item.stockOutQuantity(orderQuantity);
        applicationEventPublisher.publishEvent(new OrderItemStockOutEvent(StockOutRequest.of(item.getId(), orderQuantity))); //TODO - c-b-6-2. stockOut은 queue로 넣는구나~. 이게 어디에서 처리되는거지?
    }
}
