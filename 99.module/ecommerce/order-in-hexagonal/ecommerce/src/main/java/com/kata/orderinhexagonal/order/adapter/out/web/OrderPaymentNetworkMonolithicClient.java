package com.kata.orderinhexagonal.order.adapter.out.web;


import com.kata.orderinhexagonal.payment.adapter.out.persistence.PaymentEntity;
import com.kata.orderinhexagonal.payment.adapter.out.persistence.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class OrderPaymentNetworkMonolithicClient implements OrderPaymentNetworkClient {

    private final PaymentRepository paymentRepository; //외부 module의 repository를 바로 사용할 수 있구나?

    //TODO - c-b-8-3. 왜 결제 취소할 때 굳이 payment에서 order 모듈로 보낸 후 처리하는건가? payment에서 처리해도 되잖아?
    @Override
    @Transactional
    public void cancelPaymentRequest(Long orderId) {
        PaymentEntity paymentEntity = paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));

        paymentEntity.paymentCancellationRequest();
    }
}
