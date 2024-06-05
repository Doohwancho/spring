package com.kata.orderinhexagonal.order.adapter.out.web;

import com.kata.orderinhexagonal.member.domain.Member;
import com.kata.orderinhexagonal.order.application.port.out.LoadOrdererPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoadOrdererAdapter implements LoadOrdererPort {

    private final ItemOrdererNetworkClient itemOrdererNetworkClient;
    @Override
    public Member load(Long ordererId) {
        return itemOrdererNetworkClient.loadOrderer(ordererId);
    }
}
