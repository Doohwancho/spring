package com.kata.orderinhexagonal.discount.adapter.out.persistence;

import com.kata.orderinhexagonal.discount.application.port.out.CreateDiscountPort;
import com.kata.orderinhexagonal.discount.application.port.out.DiscountPolicyValidator;
import com.kata.orderinhexagonal.discount.domain.Discount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateDiscountAdapter implements DiscountPolicyValidator, CreateDiscountPort {

    private final DiscountRepository discountRepository;
    private final DiscountMapper discountMapper;

    @Override
    public boolean existsDiscountPolicy(Long itemId) {
        return discountRepository.existsById(itemId);
    }

    public void save(Discount discount) {
        DiscountEntity discountEntity = discountMapper.toEntity(discount);
        discountRepository.save(discountEntity);
        discount.assignId(discountEntity.getId());
    }
}
