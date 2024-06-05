package com.kata.orderinhexagonal.order.adapter.out.persistence;

import com.kata.orderinhexagonal.order.application.port.out.CancelOrderPort;
import com.kata.orderinhexagonal.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CancelOrderAdapter implements CancelOrderPort {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public void cancel(Order cancelOrder) {
        //case1) hard delete
//        OrderEntity orderEntity = orderMapper.toEntity(order);
//        orderRepository.delete(orderEntity);
//        System.out.println("Order " + order.getId() + " is canceled");

        //TODO - c-b-6-12. cancel order은 soft delete for later customer behavior analysis for market
        //case2) soft delete for layer customer behavior analysis (for marketer)
        OrderEntity orderEntity = orderRepository.findById(cancelOrder.getId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        cancelOrder.cancel();
        orderEntity.cancel();
    }
}
