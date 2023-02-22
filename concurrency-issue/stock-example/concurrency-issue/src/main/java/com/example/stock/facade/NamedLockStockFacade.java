package com.example.stock.facade;

import com.example.stock.repository.LockRepository;
import com.example.stock.service.database.NamedLockStockService;
import com.example.stock.service.java.StockService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class NamedLockStockFacade {

    private LockRepository lockRepository;

    private NamedLockStockService stockService;

    public NamedLockStockFacade(LockRepository lockRepository, NamedLockStockService stockService) {
        this.lockRepository = lockRepository;
        this.stockService = stockService;
    }

    //case5) named lock
    @Transactional
    public void decrease(Long id, Long quantity) {
        try {
            lockRepository.getLock(id.toString()); //get lock후,
            stockService.decrease(id, quantity); //update가 끝나면,
        } finally {
            lockRepository.releaseLock(id.toString()); //lock 반환
        }
    }
}