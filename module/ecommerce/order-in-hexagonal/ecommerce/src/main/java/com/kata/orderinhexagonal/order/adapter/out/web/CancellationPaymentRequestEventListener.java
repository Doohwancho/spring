package com.kata.orderinhexagonal.order.adapter.out.web;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CancellationPaymentRequestEventListener {
    private final OrderPaymentNetworkClient orderPaymentNetworkClient;

    @Async
    @EventListener
    public void cancellation(CancellationPaymentRequestEvent event) { //TODO - c-b-8-2. 결제 취소 async 요청날린걸 order 모듈에 eventlistener에서 잡아서 처리
        orderPaymentNetworkClient.cancelPaymentRequest(event.getOrderId());
    }
}
