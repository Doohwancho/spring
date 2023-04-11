package com.kata.orderinhexagonal.order.adapter.in.web;


import com.kata.orderinhexagonal.auth.JwtProvider;
import com.kata.orderinhexagonal.order.application.port.in.*;
import com.kata.orderinhexagonal.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final JwtProvider jwtProvider;

    private final CreateOrderUsecase createOrderUsecase;

    private final CancelOrderUsecase cancelOrderUsecase;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public CreateOrderResponse createOrder(@RequestHeader(value = "Authorization") String authorization, @RequestBody @Valid CreateOrderRequest request, Errors errors) {
        if (errors.hasErrors()) {
            throw new IllegalArgumentException(errors.getAllErrors().toString());
        }
        Long memberId = jwtProvider.parseToken(authorization.substring(7));
        request.assignOrdererId(memberId);

        Order order = createOrderUsecase.createOrder(request);

        return new CreateOrderResponse(order);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public CancelOrderResponse cancelOrder(@RequestHeader(value = "Authorization") String authorization, @RequestBody CancelOrderRequest request) {
        Long memberId = jwtProvider.parseToken(authorization.substring(7));
        request.assignOrdererId(memberId);

        Order cancelOrder = cancelOrderUsecase.cancelOrder(request);
        return new CancelOrderResponse(cancelOrder);
    }
}
