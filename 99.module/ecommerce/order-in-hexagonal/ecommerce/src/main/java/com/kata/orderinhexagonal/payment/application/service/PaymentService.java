package com.kata.orderinhexagonal.payment.application.service;

import com.kata.orderinhexagonal.order.domain.Order;
import com.kata.orderinhexagonal.order.domain.OrderStatus;
import com.kata.orderinhexagonal.payment.application.port.in.PaymentRequest;
import com.kata.orderinhexagonal.payment.application.port.in.PaymentUsecase;
import com.kata.orderinhexagonal.payment.application.port.out.OrderLoadPort;
import com.kata.orderinhexagonal.payment.application.port.out.PaymentSavePort;
import com.kata.orderinhexagonal.payment.application.port.out.RequestPay;
import com.kata.orderinhexagonal.payment.application.port.out.RequestPayPort;
import com.kata.orderinhexagonal.payment.domain.Payment;
import com.kata.orderinhexagonal.payment.domain.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentUsecase {
    private final OrderLoadPort orderLoadPort;
    private final RequestPayPort requestPayPort;
    private final PaymentSavePort paymentSavePort;

    @Override
    public Payment payments(PaymentRequest request) {
        //step1) load order
        Long orderId = request.getOrderId();
        Order order = orderLoadPort.load(orderId);

        //step2) validate
        if(!OrderStatus.NOT_PAYED.equals(order.getStatus())) {
            throw new IllegalStateException("주문 상태가 잘못되었습니다.");
        }

        //step3) pay using 외부 연동 api
        RequestPay requestPay = RequestPay.of(request.getCardType(), request.getCardCompany(), request.getCardNumber(),
                request.getCardCvc(),
                order.getTotalPrice(), request.getPaymentType());
        PaymentStatus paymentStatus = requestPayPort.pay(requestPay);

        //step4) change order status
        if (PaymentStatus.OK.equals(paymentStatus)) {
            order.payed();
        }

        //step5) save payment
        Payment payment = new Payment(order, request.getPaymentType(), request.getCardType(), request.getCardCompany(),
                request.getCardNumber(), request.getCardCvc(), paymentStatus);
        paymentSavePort.save(payment);

        return payment;
    }
}
