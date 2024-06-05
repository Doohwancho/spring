package com.kata.orderinhexagonal.discount.application.port.in;

import com.kata.orderinhexagonal.discount.domain.DiscountType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemDiscountRequest {
    @NotNull
    @Min(1)
    private Long itemId;
    @NotNull
    private DiscountType discountType;
    @NotNull
    @Min(1)
    private int discountRate;


    public static ItemDiscountRequest of(Long itemId, DiscountType discountType, int discountRate) {
        return new ItemDiscountRequest(itemId, discountType, discountRate);
    }
}
