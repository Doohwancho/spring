package com.kata.orderinhexagonal.fixture;

import com.kata.orderinhexagonal.item.domain.Item;
import com.kata.orderinhexagonal.stock.application.port.in.StockInRequest;
import com.kata.orderinhexagonal.stock.application.port.in.StockInUsecase;
import com.kata.orderinhexagonal.stock.domain.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockFixture {

    @Autowired
    StockInUsecase stockInUsecase;

    public Stock stockIn(Item item, int stockInQuantity) {
        return stockInUsecase.stockIn(StockInRequest.of(item.getId(), stockInQuantity));
    }
}
