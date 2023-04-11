package com.kata.orderinhexagonal.order.application.port.in;

import com.kata.orderinhexagonal.order.domain.Order;
import com.kata.orderinhexagonal.order.domain.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateOrderResponse {
    private Long id;
    private List<OrderItemResponse> orderItems = new ArrayList<>();
    private int totalPrice;
    private OrderStatus status;

    public CreateOrderResponse(Order order) {
        this.id = order.getId();
        this.totalPrice = order.getTotalPrice();
        this.status = order.getStatus();
        order.getOrderItems().forEach(orderItem -> orderItems.add(new OrderItemResponse(orderItem.getId(), orderItem.getOrderPrice(), orderItem.getOrderQuantity())));
    }
}
