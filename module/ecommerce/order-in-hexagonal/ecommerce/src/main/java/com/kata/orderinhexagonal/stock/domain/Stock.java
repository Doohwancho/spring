package com.kata.orderinhexagonal.stock.domain;

import com.kata.orderinhexagonal.item.domain.Item;
import lombok.Getter;

@Getter
public abstract class Stock {
    protected Long id;
    protected int quantity;
    protected Item item;

    public Stock(int quantity, Item item) {
        this.quantity = quantity;
        this.item = item;
    }

    public void assignId(Long id) {
        this.id = id;
    }

    public enum StockType{
        STOCK_IN, STOCK_OUT
    }

    public abstract StockType getStockType();
}
