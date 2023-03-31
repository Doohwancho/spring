package com.kata.orderinhexagonal.stock.adapter.out.persistence;

import com.kata.orderinhexagonal.item.adapter.out.persistence.ItemMapper;
import com.kata.orderinhexagonal.stock.application.port.out.SaveStockPort;
import com.kata.orderinhexagonal.stock.domain.Stock;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Component
public class SaveStockAdapter implements SaveStockPort {
    private final StockRepository stockRepository;
    private final StockMapper StockMapper;

    @Transactional
    @Override
    public Stock save(Stock stock) {
        StockEntity stockEntity = StockMapper.toEntity(stock);
        stockRepository.save(stockEntity);
        stock.assignId(stockEntity.getId());
        return stock;
    }
}
