package com.kata.orderinhexagonal.payment.adapter.out.persistence;

import com.kata.orderinhexagonal.order.adapter.out.persistence.OrderEntity;
import com.kata.orderinhexagonal.payment.domain.CardCompany;
import com.kata.orderinhexagonal.payment.domain.CardType;
import com.kata.orderinhexagonal.payment.domain.PaymentStatus;
import com.kata.orderinhexagonal.payment.domain.PaymentType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentEntity {

    @Id
    @GeneratedValue
    @Column(name = "payment_id")
    private Long id;

    @OneToOne(fetch = javax.persistence.FetchType.LAZY)
    @JoinColumn(name = "order_id", unique = true, nullable = false)
    private OrderEntity order;
    @Column(nullable = false)
    private String cardNumber;
    @Column(nullable = false)
    private String cardCvc;
    @Column(nullable = false)
    private Integer paymentPrice;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @Enumerated(EnumType.STRING)
    private CardType cardType;
    @Enumerated(EnumType.STRING)
    private CardCompany cardCompany;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    public PaymentEntity(OrderEntity orderEntity, String cardNumber, String cardCvc, int paymentPrice,
                         PaymentType paymentType, CardType cardType, CardCompany cardCompany, PaymentStatus status) {
        this.order = orderEntity;
        this.cardNumber = cardNumber;
        this.cardCvc = cardCvc;
        this.paymentPrice = paymentPrice;
        this.paymentType = paymentType;
        this.cardType = cardType;
        this.cardCompany = cardCompany;
        this.status = status;
    }

    public void paymentCancellationRequest() {
        this.status = PaymentStatus.CANCELLATION_REQUEST;
    }

    public void cancel() {
        this.status = PaymentStatus.CANCELED;
    }
}
