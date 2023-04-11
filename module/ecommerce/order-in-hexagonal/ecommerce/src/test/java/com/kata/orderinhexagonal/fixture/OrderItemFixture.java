package com.kata.orderinhexagonal.fixture;

import com.kata.orderinhexagonal.order.adapter.out.persistence.OrderItemEntity;
import com.kata.orderinhexagonal.order.adapter.out.persistence.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderItemFixture {

    @Autowired
    OrderItemRepository orderItemRepository;


    public List<OrderItemEntity> getOrderItems(Long id) {
        return orderItemRepository.findByOrderId(id);
    }

}