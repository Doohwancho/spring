package com.kata.orderinhexagonal.stock.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockEntity, Long> {
}
