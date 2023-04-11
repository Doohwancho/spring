package com.kata.orderinhexagonal.order.adapter.out.persistence;

import com.kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import com.kata.orderinhexagonal.item.domain.Item;
import com.kata.orderinhexagonal.order.domain.Order;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //casecade를 쓰지 않는 이유는, orderItem을 삭제하면 item도 삭제되면 안되기 때문이다.
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
