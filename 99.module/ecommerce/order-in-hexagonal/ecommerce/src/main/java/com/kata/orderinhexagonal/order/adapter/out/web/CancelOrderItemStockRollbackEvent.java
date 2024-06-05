package com.kata.orderinhexagonal.order.adapter.out.web;

import com.kata.orderinhexagonal.stock.application.port.in.StockInRequest;
import com.kata.orderinhexagonal.stock.application.port.in.StockOutRequest;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CancelOrderItemStockRollbackEvent {
    private final StockInRequest stockInRequest;
    public CancelOrderItemStockRollbackEvent(StockInRequest request) {
        this.stockInRequest = request;
    }
}
