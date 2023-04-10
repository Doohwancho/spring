package com.kata.orderinhexagonal.discount.adapter.out.persistence;

import com.kata.orderinhexagonal.discount.domain.DiscountType;
import com.kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import com.kata.orderinhexagonal.item.domain.Item;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiscountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemEntity item;

    @Enumerated(EnumType.STRING)
    private DiscountType discountType;
    private int discountRate;


    public DiscountEntity(ItemEntity item, DiscountType discountType, int discountRate) {
        this.item = item;
        this.discountType = discountType;
        this.discountRate = discountRate;
    }

    public DiscountEntity(Long id, Item item, DiscountType discountType, int discountRate) {
        this.id = id;
        this.item = new ItemEntity(item.getId(), item.getName(), item.getPrice(), item.getStockQuantity(), this);
        this.discountType = discountType;
        this.discountRate = discountRate;
    }
}
