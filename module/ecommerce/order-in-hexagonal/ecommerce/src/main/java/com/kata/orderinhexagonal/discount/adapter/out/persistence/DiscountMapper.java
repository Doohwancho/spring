package com.kata.orderinhexagonal.discount.adapter.out.persistence;

import com.kata.orderinhexagonal.discount.domain.Discount;
import org.springframework.stereotype.Component;

@Component
public class DiscountMapper {

    public DiscountEntity toEntity(Discount discount) {
        return new DiscountEntity(discount.getId(), discount.getItem(), discount.getDiscountType(), discount.getDiscountRate());
    }
}
