package com.kata.orderinhexagonal.order.adapter.out.web;

import com.kata.orderinhexagonal.item.domain.Item;
import com.kata.orderinhexagonal.stock.application.port.in.StockInRequest;
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

        //TODO - c-b-6-2. stockOut은 queue로 넣는구나~. 이게 어디에서 처리되는거지?
        //A. stock in, stock out은 비동기 queue로 처리되고, 이는 sudo-code로 구현되었다.
        //   repository에는 stock in, stock out이 반영되지 않는다.
        applicationEventPublisher.publishEvent(new OrderItemStockOutEvent(StockOutRequest.of(item.getId(), orderQuantity)));
    }

    @Override
    public void stockIn(Item cancelOrderItem, int orderQuantity) {
        cancelOrderItem.stockInQuantity(orderQuantity);

        //TODO - c-b-6-2. stockOut은 queue로 넣는구나~. 이게 어디에서 처리되는거지?
        //A. stock in, stock out은 비동기 queue로 처리되고, 이는 sudo-code로 구현되었다.
        //   repository에는 stock in, stock out이 반영되지 않는다.
        applicationEventPublisher.publishEvent(new CancelOrderItemStockRollbackEvent(StockInRequest.of(cancelOrderItem.getId(), orderQuantity)));
    }
}
