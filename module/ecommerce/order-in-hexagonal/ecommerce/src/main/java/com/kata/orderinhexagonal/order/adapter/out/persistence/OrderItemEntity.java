package com.kata.orderinhexagonal.order.adapter.out.persistence;

import com.kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import com.kata.orderinhexagonal.item.domain.Item;
import com.kata.orderinhexagonal.order.domain.Order;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class OrderItemEntity {
    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private ItemEntity item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @Column(nullable = false)
    private int orderPrice;

    @Column(nullable = false)
    private int orderQuantity;

    public OrderItemEntity(int orderQuantity, int orderPrice, ItemEntity itemEntity) {
        this.orderQuantity = orderQuantity;
        this.orderPrice = orderPrice;
        this.item = itemEntity;
    }

    public void setOrder(OrderEntity orderEntity) {
        this.order = orderEntity;
    }
}
