package com.kata.orderinhexagonal.discount.domain;

import com.kata.orderinhexagonal.item.domain.Item;
import lombok.Getter;

@Getter
public class Discount {
    private Long id;
    private Item item;
    private DiscountType discountType;
    private int discountRate;

    public Discount(Item item, DiscountType discountType, int discountRate) {
        this.item = item;
        this.discountType = discountType;
        this.discountRate = discountRate;
    }

    public void assignId(Long id) {
        this.id = id;
    }
}
