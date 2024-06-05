package com.kata.orderinhexagonal.payment.adapter.out.api;

import com.kata.orderinhexagonal.payment.domain.CardType;
import com.kata.orderinhexagonal.payment.domain.PaymentType;

public interface PayClient {
    void process(CardType cardType, PaymentType paymentType, String cardNumber, String cardCvc, int paymentPrice);

}
