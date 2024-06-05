package com.kata.orderinhexagonal.stock.adapter.out.persistence;

import com.kata.orderinhexagonal.stock.domain.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {

    public StockEntity toEntity(Stock stock) {
        return new StockEntity(stock.getId(), stock.getQuantity(), stock.getStockType());
    }
}
