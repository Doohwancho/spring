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
        orderRepository.save(orderEntity); //error! -> 이 떄, orderEntity안에 orderItemEntity안에 itemEntity의 id가 자동으로 3으로 들어가는데, 이걸 원래 id를 받아오는걸로 바꿔야 한다.
        order.assignId(orderEntity.getId());

        for (int i = 0; i < orderEntity.getOrderItems().size(); i++) {
            OrderItemEntity orderItemEntity = orderEntity.getOrderItems().get(i);
            OrderItem orderItem = order.getOrderItems().get(i);
            orderItem.assignId(orderItemEntity.getId());
        }
    }
}
