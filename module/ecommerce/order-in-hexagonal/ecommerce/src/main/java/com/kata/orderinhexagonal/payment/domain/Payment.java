package com.kata.orderinhexagonal.payment.domain;

import com.kata.orderinhexagonal.order.domain.Order;
import lombok.Getter;

@Getter
public class Payment {
    private Long id;
    private Order order;
    private PaymentType paymentType;
    private CardType cardType;
    private CardCompany cardCompany;
    private String cardNumber;
    private String cardCvc;
    private PaymentStatus status;

    public Payment(Order order, PaymentType paymentType, CardType cardType, CardCompany cardCompany, String cardNumber,
                   String cardCvc, PaymentStatus paymentStatus) {
        this.order = order;
        this.paymentType = paymentType;
        this.cardType = cardType;
        this.cardCompany = cardCompany;
        this.cardNumber = cardNumber;
        this.cardCvc = cardCvc;
        this.status = paymentStatus;
    }

    public void assignId(Long id) {
        this.id = id;
    }
}