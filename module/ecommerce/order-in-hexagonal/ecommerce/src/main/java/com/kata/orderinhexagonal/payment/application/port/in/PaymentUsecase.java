package com.kata.orderinhexagonal.payment.application.port.in;

import com.kata.orderinhexagonal.payment.domain.Payment;

public interface PaymentUsecase {
    Payment payments(PaymentRequest request);
}