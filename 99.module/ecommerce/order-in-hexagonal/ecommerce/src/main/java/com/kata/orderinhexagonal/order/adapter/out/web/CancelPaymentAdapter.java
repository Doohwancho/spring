package com.kata.orderinhexagonal.order.adapter.out.web;


import com.kata.orderinhexagonal.order.application.port.out.CancelPaymentPort;
import com.kata.orderinhexagonal.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CancelPaymentAdapter implements CancelPaymentPort {

    private final ApplicationEventPublisher eventPublisher;


    @Override
    public void request(Order order) {
        eventPublisher.publishEvent(new CancellationPaymentRequestEvent(order.getId()));
    }
}
