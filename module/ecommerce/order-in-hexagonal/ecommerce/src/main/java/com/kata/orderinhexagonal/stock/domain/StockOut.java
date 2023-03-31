package com.kata.orderinhexagonal.stock.domain;

import com.kata.orderinhexagonal.item.domain.Item;

public class StockOut extends Stock {
    public StockOut(int quantity, Item item) {
        super(quantity, item);
    }

    @Override
    public StockType getStockType() {
        return StockType.STOCK_OUT;
    }
}
