package com.kata.orderinhexagonal.order.adapter.out.persistence;

import com.kata.orderinhexagonal.order.application.port.out.SaveOrderPort;
import com.kata.orderinhexagonal.order.domain.Order;
import com.kata.orderinhexagonal.order.domain.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateOrderAdapter implements SaveOrderPort {

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    @Override
    public void save(Order order) {
        OrderEntity orderEntity = orderMapper.toEntity(order);
        orderRepository.save(orderEntity);
        order.assignId(orderEntity.getId());

        for (int i = 0; i < orderEntity.getOrderItems().size(); i++) {
            OrderItemEntity orderItemEntity = orderEntity.getOrderItems().get(i);
            OrderItem orderItem = order.getOrderItems().get(i);
            orderItem.assignId(orderItemEntity.getId());
        }
    }
}
