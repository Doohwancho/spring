package com.example.stock.service.database;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NamedLockStockService {
    private final StockRepository stockRepository;

    public NamedLockStockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    /**
     * Q. what is Propagation within @Transactional?
     *
     * A. Propagation.REQUIRES_NEW attribute ensures that a new transaction is started for this method,
     *      and any existing transaction is suspended.
     *   This ensures that any concurrent transactions do not interfere with each other and that the data remains consistent.
     *
     */

    //case5) named lock
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public synchronized void decrease(Long id, Long quantity) {
        Stock stock = stockRepository.findById(id).orElseThrow();

        stock.decrease(quantity);

        stockRepository.saveAndFlush(stock);
    }
}
