package com.kata.orderinhexagonal.order.application.port.in;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemRequest {
    @NotNull(message = "주문할 상품의 ID가 없습니다.")
    private Long itemId;
    @NotNull(message = "주문할 상품의 개수를 입력해주세요.")
    @Min(value = 1, message = "주문할 상품의 개수를 1개 이상으로 입력해주세요.")
    private Integer orderQuantity;

    public OrderItemRequest(Long itemId, Integer orderQuantity) {
        this.itemId = itemId;
        this.orderQuantity = orderQuantity;
    }

    public static OrderItemRequest of(Long itemId, int orderQuantity) {
        return new OrderItemRequest(itemId, orderQuantity);
    }
}
