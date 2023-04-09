package com.kata.orderinhexagonal.order.domain;

import com.kata.orderinhexagonal.item.domain.Item;
import lombok.Getter;

@Getter
public class OrderItem {
    private Long id;
    private Order order;
    private Item item;
    private int orderQuantity;
    private int orderPrice;

    public OrderItem(Order order, Item item, int orderQuantity, int orderPrice) {
        this.order = order;
        this.item = item;
        this.orderQuantity = orderQuantity;
        this.orderPrice = orderPrice;
    }

    public void assignId(Long id) {
        this.id = id;
    }
}
