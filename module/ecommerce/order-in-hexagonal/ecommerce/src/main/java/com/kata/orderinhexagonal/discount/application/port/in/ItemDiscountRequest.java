package com.kata.orderinhexagonal.discount.application.port.in;

import com.kata.orderinhexagonal.discount.domain.DiscountType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemDiscountRequest {
    private Long itemId;
    private DiscountType discountType;
    private int discountRate;


    public static ItemDiscountRequest of(Long itemId, DiscountType discountType, int discountRate) {
        return new ItemDiscountRequest(itemId, discountType, discountRate);
    }
}
