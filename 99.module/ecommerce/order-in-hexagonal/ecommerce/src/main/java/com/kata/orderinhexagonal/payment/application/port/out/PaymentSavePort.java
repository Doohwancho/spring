package com.kata.orderinhexagonal.payment.application.port.out;

import com.kata.orderinhexagonal.payment.domain.Payment;

public interface PaymentSavePort {
    void save(Payment payment);
}
