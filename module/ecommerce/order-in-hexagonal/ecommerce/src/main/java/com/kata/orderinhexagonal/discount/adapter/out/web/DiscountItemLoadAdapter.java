package com.kata.orderinhexagonal.discount.adapter.out.web;

import com.kata.orderinhexagonal.discount.application.port.out.DiscountItemLoadPort;
import com.kata.orderinhexagonal.item.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DiscountItemLoadAdapter implements DiscountItemLoadPort {

    private final DiscountItemNetworkClient discountItemNetworkClient;

    @Override
    public Item loadItem(Long itemId) {
        return discountItemNetworkClient.loadItem(itemId);
    }
}
