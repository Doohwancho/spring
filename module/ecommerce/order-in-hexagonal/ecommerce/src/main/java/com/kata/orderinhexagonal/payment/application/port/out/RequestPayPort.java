package com.kata.orderinhexagonal.payment.application.port.out;

import com.kata.orderinhexagonal.payment.domain.PaymentStatus;

public interface RequestPayPort {
    PaymentStatus pay(RequestPay requestPay);
}
