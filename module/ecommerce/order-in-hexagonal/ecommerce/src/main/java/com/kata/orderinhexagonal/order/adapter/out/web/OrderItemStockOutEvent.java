package com.kata.orderinhexagonal.order.adapter.out.web;

import com.kata.orderinhexagonal.stock.application.port.in.StockOutRequest;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class OrderItemStockOutEvent  {
    private final StockOutRequest stockOutRequest;
    public OrderItemStockOutEvent(StockOutRequest stockOutRequest) {
        this.stockOutRequest = stockOutRequest;
    }
}
