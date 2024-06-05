package com.kata.orderinhexagonal.stock.application.port.in;


import com.kata.orderinhexagonal.stock.domain.Stock;

public interface StockOutUsecase {
    Stock stockOut(StockOutRequest request);
}