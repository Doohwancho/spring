package com.kata.orderinhexagonal.order.adapter.out.persistence;

import com.kata.orderinhexagonal.member.adapter.out.persistence.MemberEntity;
import com.kata.orderinhexagonal.member.domain.Member;
import com.kata.orderinhexagonal.order.domain.OrderItem;
import com.kata.orderinhexagonal.order.domain.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Enumerated(EnumType.STRING) //EnumType.ORDINAL은 숫자로 저장되기 때문에 변경이 되면 문제가 생긴다.
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemEntity> orderItems = new ArrayList<>();

    public OrderEntity(Long id, OrderStatus status) {
        this.id = id;
        this.status = status;
    }

    public void setMember(MemberEntity member) {
        this.member = member;
        member.getOrders().add(this); //주인에서 하인을 더할 수 있다.
    }

    public void initMember(MemberEntity memberEntity) {
        this.member = memberEntity;
    }

    public void addOrderItem(OrderItemEntity orderItemEntity) {
        orderItemEntity.setOrder(this);
        this.orderItems.add(orderItemEntity);
    }
}
