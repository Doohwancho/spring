package com.cho.order.presentation;

import com.cho.order.application.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/orders/users/{userId}")
    public Long placeOrder(@PathVariable("userId") Long userId) {
        return orderService.placeOrder(userId);
    }

    @PutMapping("/orders/{orderId}")
    public void cancelOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancelOrder(orderId);
    }
}
