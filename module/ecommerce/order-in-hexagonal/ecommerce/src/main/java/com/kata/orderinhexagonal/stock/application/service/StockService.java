package com.kata.orderinhexagonal.stock.application.service;

import com.kata.orderinhexagonal.item.domain.Item;
import com.kata.orderinhexagonal.stock.application.port.in.StockInRequest;
import com.kata.orderinhexagonal.stock.application.port.in.StockInUsecase;
import com.kata.orderinhexagonal.stock.application.port.in.StockOutRequest;
import com.kata.orderinhexagonal.stock.application.port.in.StockOutUsecase;
import com.kata.orderinhexagonal.stock.application.port.out.LoadItemPort;
import com.kata.orderinhexagonal.stock.application.port.out.SaveStockPort;
import com.kata.orderinhexagonal.stock.domain.Stock;
import com.kata.orderinhexagonal.stock.domain.StockIn;
import com.kata.orderinhexagonal.stock.domain.StockOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StockService implements StockInUsecase, StockOutUsecase {

    private final LoadItemPort loadItemPort;
    private final SaveStockPort saveStockPort;

    public Stock stockIn(StockInRequest request) {
        Item item = loadItemPort.load(request.getItemId());
        Integer stockInQuantity = request.getQuantity();
        item.stockInQuantity(stockInQuantity);
        Stock stock = new StockIn(request.getQuantity(), item);
        return saveStockPort.save(stock);
    }

    public Stock stockOut(StockOutRequest request) {
        Item item = loadItemPort.load(request.getId());
        Integer stockOutQuantity = request.getQuantity();
        item.stockOutQuantity(stockOutQuantity);
        Stock stock = new StockOut(request.getQuantity(), item);
        return saveStockPort.save(stock);
    }
}