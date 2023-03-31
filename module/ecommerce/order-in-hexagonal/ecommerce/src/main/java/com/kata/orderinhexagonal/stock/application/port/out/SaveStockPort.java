package com.kata.orderinhexagonal.stock.application.port.out;

import com.kata.orderinhexagonal.stock.domain.Stock;

public interface SaveStockPort {
    Stock save(Stock stock);
}
