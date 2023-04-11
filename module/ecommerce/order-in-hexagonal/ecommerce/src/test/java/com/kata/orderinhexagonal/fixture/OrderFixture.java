package com.kata.orderinhexagonal.fixture;

import com.kata.orderinhexagonal.item.domain.Item;
import com.kata.orderinhexagonal.order.adapter.out.persistence.OrderEntity;
import com.kata.orderinhexagonal.order.adapter.out.persistence.OrderMapper;
import com.kata.orderinhexagonal.order.adapter.out.persistence.OrderRepository;
import com.kata.orderinhexagonal.order.application.port.in.CreateOrderRequest;
import com.kata.orderinhexagonal.order.application.port.in.CreateOrderUsecase;
import com.kata.orderinhexagonal.order.application.port.in.OrderItemRequest;
import com.kata.orderinhexagonal.order.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderFixture {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CreateOrderUsecase createOrderUsecase;

    @Autowired
    ItemFixture itemFixture;

    @Autowired
    StockFixture stockFixture;

    @Autowired
    OrderMapper orderMapper;

    public OrderEntity getOrderEntity(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    public void clearOrder() {
        orderRepository.deleteAll();
    }

    public Order createOrder(long ordererId) {
        Item item = itemFixture.createItem("노트북", 1_000_000);
        stockFixture.stockIn(item, 10);
        CreateOrderRequest request = CreateOrderRequest.of(List.of(
                OrderItemRequest.of(item.getId(), 1)
        ));
        request.assignOrdererId(ordererId);
        return createOrderUsecase.createOrder(request);
    }

    public Order createOrderWithMultipleItems(long ordererId){

        Item item1 = itemFixture.createItem("노트북", 1_000_000);
        stockFixture.stockIn(item1, 10);

        Item item2 = itemFixture.createItem("마우스", 10_000);
        stockFixture.stockIn(item2, 20);

        CreateOrderRequest request = CreateOrderRequest.of(List.of(
                OrderItemRequest.of(item1.getId(), 1),
                OrderItemRequest.of(item2.getId(), 2)
        ));
        request.assignOrdererId(ordererId);

        return createOrderUsecase.createOrder(request);

    }
}
