package com.cho.order.application;

import com.cho.order.domain.OrderEntity;
import com.cho.order.domain.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final RabbitTemplate rabbitTemplate;

    public Long placeOrder(Long orderer) {
        OrderEntity newOrder = new OrderEntity(orderer);
        return orderRepository.save(newOrder).getOrderId();
    }

    @Async
    @RabbitListener(queues = "refund.queue")
    public void cancelOrder(Long orderId) {
        // 주문 취소
        orderRepository.findById(orderId)
                .get()
                .cancel();

        // 이벤트 발행
        rabbitTemplate.convertAndSend("amq.topic", "refund.orderCancel", orderId);
    }
}