package com.kata.orderinhexagonal.stock.application.port.in;

import com.kata.orderinhexagonal.stock.domain.Stock;

public interface StockInUsecase {
    Stock stockIn(StockInRequest request);
}
