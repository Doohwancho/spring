package com.kata.orderinhexagonal.stock.application.service;

import com.kata.orderinhexagonal.item.domain.Item;
import com.kata.orderinhexagonal.stock.application.port.in.StockInRequest;
import com.kata.orderinhexagonal.stock.application.port.out.LoadItemPort;
import com.kata.orderinhexagonal.stock.application.port.out.SaveStockPort;
import com.kata.orderinhexagonal.stock.domain.Stock;
import com.kata.orderinhexagonal.stock.domain.StockIn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StockService {

    private final LoadItemPort loadItemPort;
    private final SaveStockPort saveStockPort;

    public Stock stockIn(StockInRequest request) {
        Item item = loadItemPort.load(request.getId());
        Stock stock = new StockIn(request.getQuantity(), item);
        return saveStockPort.save(stock);
    }
}