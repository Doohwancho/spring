package com.kata.orderinhexagonal.order.adapter.out.persistence;

import com.kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import com.kata.orderinhexagonal.item.adapter.out.persistence.ItemMapper;
import com.kata.orderinhexagonal.member.adapter.out.persistence.MemberEntity;
import com.kata.orderinhexagonal.member.adapter.out.persistence.MemberMapper;
import com.kata.orderinhexagonal.order.domain.Order;
import com.kata.orderinhexagonal.order.domain.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final MemberMapper memberMapper;
    private final ItemMapper itemMapper;

    public OrderEntity toEntity(Order order) {
        OrderEntity orderEntity = new OrderEntity(order.getId(), order.getStatus());

        MemberEntity memberEntity = memberMapper.toEntity(order.getMember());
        orderEntity.initMember(memberEntity);

        //TODO - c-b-6-7. order domain -> entity 변환 시, 그 안에 orderItem도 entity로 변환해야 한다.
        for (OrderItem orderItem : order.getOrderItems()) {
            ItemEntity itemEntity = itemMapper.toEntity(orderItem.getItem());

            OrderItemEntity orderItemEntity = new OrderItemEntity(orderItem.getOrderQuantity(),
                    orderItem.getOrderPrice(), itemEntity);

            orderEntity.addOrderItem(orderItemEntity);
        }
        return orderEntity;
    }

    public Order toDomain(OrderEntity entity) {
        return new Order(entity.getId(), memberMapper.toDomain(entity.getMember()), entity.getStatus());
    }
}
