package com.kata.orderinhexagonal.payment.application.port.out;

import com.kata.orderinhexagonal.payment.domain.CardCompany;
import com.kata.orderinhexagonal.payment.domain.CardType;
import com.kata.orderinhexagonal.payment.domain.PaymentType;
import lombok.Getter;

@Getter
public class RequestPay {
    private final CardType cardType;
    private final CardCompany cardCompany;
    private final String cardNumber;
    private final String cardCvc;
    private final int paymentPrice;
    private final PaymentType paymentType;

    public RequestPay(CardType cardType, CardCompany cardCompany, String cardNumber, String cardCvc, int paymentPrice,
                      PaymentType paymentType) {
        this.cardType = cardType;
        this.cardCompany = cardCompany;
        this.cardNumber = cardNumber;
        this.cardCvc = cardCvc;
        this.paymentPrice = paymentPrice;
        this.paymentType = paymentType;
    }

    public static RequestPay of(CardType cardType, CardCompany cardCompany, String cardNumber, String cardCvc,
                                int paymentPrice, PaymentType paymentType) {
        return new RequestPay(cardType, cardCompany, cardNumber, cardCvc, paymentPrice, paymentType);
    }
}
