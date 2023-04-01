package com.kata.orderinhexagonal.discount.application.port.in;

import com.kata.orderinhexagonal.discount.domain.Discount;
import com.kata.orderinhexagonal.discount.domain.DiscountType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ItemDiscountResponse {
    private Long itemId;
    private DiscountType discountType;
    private int discountRate;

    public static ItemDiscountResponse of(Discount discount) {
        Long itemId = discount.getItem().getId();
        DiscountType discountType = discount.getDiscountType();
        int discountRate = discount.getDiscountRate();
        return new ItemDiscountResponse(itemId, discountType, discountRate);
    }
}
