package com.kata.orderinhexagonal.order.domain;


import com.kata.orderinhexagonal.delivery.domain.DeliveryStatus;
import com.kata.orderinhexagonal.discount.domain.DiscountType;
import com.kata.orderinhexagonal.item.domain.Item;
import com.kata.orderinhexagonal.member.domain.Member;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Order {
    private Long id;
    private OrderStatus status;
    private Member member;
    private List<OrderItem> orderItems = new ArrayList<>();;

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


    //TODO - c-b-6-6. refactor - discounted price of item using switch
    public int calculateDiscountedPrice(Item item) {
        if (item.getDiscount() == null) {
            return item.getPrice();
        }

        DiscountType discountType = item.getDiscount().getDiscountType();
        return switch (discountType) {
            case PERCENTAGE -> (int) (item.getPrice() * (1 - item.getDiscount().getDiscountRate()));
            case AMOUNT -> item.getPrice() - item.getDiscount().getDiscountRate();
            default -> item.getPrice();
        };
    }

    public boolean isOrdererAndRequesterMatch(Member cancelRequester) {
        return this.getMember().getId() == cancelRequester.getId();
    }

    public boolean isCanceled() {
        return this.status == OrderStatus.CANCELED;
    }

    public boolean isPayed() {
        return this.status == OrderStatus.PAYED;
    }

    public boolean isDelivered() {
        return this.status == OrderStatus.DELIVERED;
    }

    public void cancel() {
        this.status = OrderStatus.CANCELED;
    }

    public int getTotalPrice() {
        return orderItems.stream()
                .mapToInt(orderItem -> orderItem.getOrderPrice() * orderItem.getOrderQuantity())
                .sum();
    }

    public void updateDeliveredStatus() {
        this.status = OrderStatus.DELIVERED;
    }

    public void payed() {
        this.status = OrderStatus.PAYED;
    }
}
