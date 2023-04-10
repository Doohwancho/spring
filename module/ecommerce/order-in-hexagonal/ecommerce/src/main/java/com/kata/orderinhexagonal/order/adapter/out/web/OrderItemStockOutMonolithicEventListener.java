package com.kata.orderinhexagonal.order.adapter.out.web;

import com.kata.orderinhexagonal.stock.application.port.in.StockOutUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemStockOutMonolithicEventListener {

    private final StockOutUsecase stockOutUsecase;

    //TODO - c-b-6-2. stockOut를 async방법으로 queue로 넣으면, event listener가 처리해준다.
    @Async
    @EventListener
    public void handle(OrderItemStockOutEvent event) {
        stockOutUsecase.stockOut(event.getStockOutRequest());
    }
}
