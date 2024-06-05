package com.kata.orderinhexagonal.stock.adapter.out.persistence;

import com.kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import com.kata.orderinhexagonal.item.domain.Item;
import com.kata.orderinhexagonal.stock.application.port.out.LoadItemPort;
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
    private final LoadItemPort loadItemPort;

    @Override
    @Transactional
    public Stock save(Stock stock) {
        Item targetItem = stock.getItem();
        ItemEntity itemEntity = loadItemPort.loadItemEntity(targetItem.getId());

        itemEntity.changeStockQuantity(targetItem.getStockQuantity());

        StockEntity stockEntity = StockMapper.toEntity(stock);
        stockEntity.updateItemEntity(itemEntity);

        stockRepository.save(stockEntity);
        stock.assignId(stockEntity.getId());
        return stock;
    }
}
