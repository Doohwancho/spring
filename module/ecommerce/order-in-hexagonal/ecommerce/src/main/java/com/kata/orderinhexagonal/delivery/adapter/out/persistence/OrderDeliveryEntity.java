package com.kata.orderinhexagonal.delivery.adapter.out.persistence;

import com.kata.orderinhexagonal.order.adapter.out.persistence.OrderEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDeliveryEntity {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private OrderEntity order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private DeliveryEntity delivery;

    public OrderDeliveryEntity(OrderEntity orderEntity, DeliveryEntity deliveryEntity) {
        this.order = orderEntity;
        this.delivery = deliveryEntity;
    }
}
