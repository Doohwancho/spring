package com.kata.orderinhexagonal.payment.config;

import com.kata.orderinhexagonal.payment.adapter.out.api.PayClient;
import com.kata.orderinhexagonal.payment.adapter.out.api.PayClientKata;
import com.kata.orderinhexagonal.payment.domain.CardCompany;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PaymentAppConfig {

    @Bean
    public Map<CardCompany, PayClient> payClientsMap() {
        Map<CardCompany, PayClient> payClientsMap = new HashMap<>();
        payClientsMap.put(CardCompany.KATA, payClientKata()); //임시 외부 결제 api 리스트
        return payClientsMap;
    }

    @Bean
    public PayClient payClientKata() {
        return new PayClientKata();
    }
}
