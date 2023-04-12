package com.kata.orderinhexagonal.order.adapter.out.persistence;

import com.kata.orderinhexagonal.order.application.port.out.OrderStatusChangePort;
import com.kata.orderinhexagonal.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class OrderStatusChangeAdapter implements OrderStatusChangePort {
    private final OrderRepository orderRepository;


    @Override
    @Transactional
    public void changeStatus(Order order) {
        OrderEntity entity = orderRepository.findById(order.getId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        entity.changeStatus(order.getStatus()); //TODO - c-b-7-2. OrderEntity를 .setter로 필드값 변경하면, transaction 끝나는 시점에 자동으로 update query 날려준다
    }
}
