package com.kata.orderinhexagonal.payment.adapter.out.api;

import com.kata.orderinhexagonal.payment.domain.CardType;
import com.kata.orderinhexagonal.payment.domain.PaymentType;

public class PayClientKata implements PayClient {
    @Override
    public void process(CardType cardType, PaymentType paymentType, String cardNumber, String cardCvc,
                        int paymentPrice) {
        //TODO - 미구현: 외부 결제 API 호출
        System.out.println("helloKataPayWorld!");
    }
}
