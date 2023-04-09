package com.kata.orderinhexagonal.order.domain;


import com.kata.orderinhexagonal.item.domain.Item;
import com.kata.orderinhexagonal.member.domain.Member;
import lombok.Getter;

import java.util.List;

@Getter
public class Order {
    private Long id;
    private OrderStatus status;
    private Member member;
    private List<OrderItem> orderItems;

    public Order(Member orderer) {
        this.member = orderer;
        this.status = OrderStatus.NOT_PAYED;
    }

    public Order(Long id, Member member, OrderStatus status) {
        this.id = id;
        this.member = member;
        this.status = status;
    }

    public void assignId(Long id) {
        this.id = id;
    }

    public void addOrderItem(Item item, int orderQuantity, int orderPrice) {
        orderItems.add(new OrderItem(this, item, orderQuantity, orderPrice));
    }
}
