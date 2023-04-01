package com.kata.orderinhexagonal.discount.adapter.out.persistence;

import com.kata.orderinhexagonal.discount.domain.DiscountType;
import com.kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import com.kata.orderinhexagonal.item.domain.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class DiscountEntity {
    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(name = "item_id")
    @OneToOne(fetch = javax.persistence.FetchType.LAZY)
    private ItemEntity item;

    private DiscountType discountType;
    private int discountRate;


    public DiscountEntity(ItemEntity item, DiscountType discountType, int discountRate) {
        this.item = item;
        this.discountType = discountType;
        this.discountRate = discountRate;
    }

    public DiscountEntity(Long id, Item item, DiscountType discountType, int discountRate) {
        this.id = id;
        this.item = new ItemEntity(item.getId(), item.getName(), item.getPrice(), item.getStockQuantity());
        this.discountType = discountType;
        this.discountRate = discountRate;
    }
}
