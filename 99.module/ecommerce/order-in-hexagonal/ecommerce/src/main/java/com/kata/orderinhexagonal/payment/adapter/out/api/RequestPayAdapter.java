package com.kata.orderinhexagonal.payment.adapter.out.api;

import com.kata.orderinhexagonal.payment.application.port.out.RequestPay;
import com.kata.orderinhexagonal.payment.application.port.out.RequestPayPort;
import com.kata.orderinhexagonal.payment.domain.CardCompany;
import com.kata.orderinhexagonal.payment.domain.PaymentStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class RequestPayAdapter implements RequestPayPort {
    private final Map<CardCompany, PayClient> payClientsMap;

    //TODO - c-b-8-1. 외부 api 호출은 adapter.out.api에서 하네?
    @Override
    public PaymentStatus pay(RequestPay requestPay) {
        try {
            PayClient payClient = this.payClientsMap.get(requestPay.getCardCompany());
            payClient.process(requestPay.getCardType(), requestPay.getPaymentType(), requestPay.getCardNumber(),
                    requestPay.getCardCvc(), requestPay.getPaymentPrice());
            return PaymentStatus.OK;
        } catch (Exception e) {
            return PaymentStatus.FAILED;
        }
    }
}
