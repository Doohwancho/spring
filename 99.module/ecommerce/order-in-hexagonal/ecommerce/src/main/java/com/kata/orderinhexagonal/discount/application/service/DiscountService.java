package com.kata.orderinhexagonal.discount.application.service;

import com.kata.orderinhexagonal.discount.adapter.out.persistence.CreateDiscountAdapter;
import com.kata.orderinhexagonal.discount.application.port.in.CreateItemDiscountUsecase;
import com.kata.orderinhexagonal.discount.application.port.in.ItemDiscountRequest;
import com.kata.orderinhexagonal.discount.application.port.out.CreateDiscountPort;
import com.kata.orderinhexagonal.discount.application.port.out.DiscountItemLoadPort;
import com.kata.orderinhexagonal.discount.application.port.out.DiscountPolicyValidator;
import com.kata.orderinhexagonal.discount.domain.Discount;
import com.kata.orderinhexagonal.item.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DiscountService implements CreateItemDiscountUsecase {

    private final DiscountItemLoadPort discountItemLoadPort;
    private final DiscountPolicyValidator discountPolicyValidator;
    private final CreateDiscountPort createDiscountPort;

    public Discount applyDiscount(ItemDiscountRequest request) {
        Item item = discountItemLoadPort.loadItem(request.getItemId());
        if(discountPolicyValidator.existsDiscountPolicy(item.getId())){
            throw new RuntimeException("Discount policy already exists");
        }
        Discount discount = new Discount(item, request.getDiscountType(), request.getDiscountRate());

        createDiscountPort.save(discount);

        return discount;
    }
}
