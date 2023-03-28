package com.example.appkata.module.order.api;

import com.example.appkata.module.order.application.OrderService;
import com.example.appkata.module.order.application.dto.CreateOrderRequest;
import com.example.appkata.module.order.application.dto.CreateOrderResponse;
import com.example.appkata.module.order.application.dto.FindOrderResponse;
import com.example.appkata.module.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderApi {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateOrderResponse order(@RequestBody CreateOrderRequest request) {
        Order order = orderService.order(request);
        return new CreateOrderResponse(order);
    }

    @GetMapping("/{orderId}")
    public FindOrderResponse findOrder(@PathVariable("orderId") long orderId) {
        Order order = orderService.findOrder(orderId);
        return new FindOrderResponse(order);
    }

}
