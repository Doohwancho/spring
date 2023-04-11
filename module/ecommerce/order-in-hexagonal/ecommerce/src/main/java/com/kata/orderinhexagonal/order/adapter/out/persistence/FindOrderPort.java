package com.kata.orderinhexagonal.order.adapter.out.persistence;

import com.kata.orderinhexagonal.item.adapter.out.persistence.ItemMapper;
import com.kata.orderinhexagonal.order.application.port.out.LoadOrderPort;
import com.kata.orderinhexagonal.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindOrderPort implements LoadOrderPort {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderItemRepository orderItemRepository;
    private final ItemMapper itemMapper;

    @Override
    public Order loadOrder(Long orderId) {
        OrderEntity orderEntity = orderRepository.findOrderWithMemberId(orderId).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        Order order = orderMapper.toDomain(orderEntity);

        //TODO - c-b-6-11. orderItem이 erd상에는 order에 속해있지 않지만, 필요하여 domain에 껴있으니, order를 찾은 후, orderItem도 찾아 더해준다.
        orderItemRepository.findByOrderId(orderId).forEach(orderItemEntity -> {
            order.addOrderItem(itemMapper.toDomain(orderItemEntity.getItem()), orderItemEntity.getOrderQuantity(), orderItemEntity.getOrderPrice());
        });
        return order;
    }

    @Override
    public OrderEntity loadOrderEntity(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }
}
