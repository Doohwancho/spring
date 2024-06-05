package com.kata.orderinhexagonal.payment.application.port.in;

import com.kata.orderinhexagonal.payment.domain.CardType;
import com.kata.orderinhexagonal.payment.domain.PaymentType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentResponse {
    private Long id;
    private Long orderId;
    private int paymentPrice;
    private PaymentType paymentType;
    private CardType cardType;

    public PaymentResponse(Long id, Long orderId, int paymentPrice, PaymentType paymentType, CardType cardType) {
        this.id = id;
        this.orderId = orderId;
        this.paymentPrice = paymentPrice;
        this.paymentType = paymentType;
        this.cardType = cardType;
    }
}
