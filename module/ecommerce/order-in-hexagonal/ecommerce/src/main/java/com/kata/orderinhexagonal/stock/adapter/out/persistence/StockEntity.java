package com.kata.orderinhexagonal.stock.adapter.out.persistence;

import com.kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import com.kata.orderinhexagonal.stock.domain.Stock;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StockEntity {

    @Id
    @GeneratedValue
    private Long id;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private Stock.StockType stockType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemEntity itemEntity;

    public StockEntity(Long id, Integer quantity, Stock.StockType stockType) {
        this.id = id;
        this.quantity = quantity;
        this.stockType = stockType;
    }

    public void updateItemEntity(ItemEntity itemEntity) {
        this.itemEntity = itemEntity;
    }
}
