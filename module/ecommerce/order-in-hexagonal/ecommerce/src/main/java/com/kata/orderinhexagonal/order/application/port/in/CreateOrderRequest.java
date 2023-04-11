package com.kata.orderinhexagonal.order.application.port.in;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateOrderRequest {
    @NotNull(message = "주문자 ID가 없습니다.")
    private Long ordererId;
    @NotEmpty(message = "주문할 상품을 추가해 주세요.")
    @Size(min = 1, message = "주문할 상품을 추가해 주세요.")
    private List<OrderItemRequest> orderItemRequests = new ArrayList<>();

    public CreateOrderRequest(List<OrderItemRequest> orderItemRequest) {
        this.orderItemRequests = orderItemRequest;
    }

    public static CreateOrderRequest of(List<OrderItemRequest> orderItemRequest) {
        return new CreateOrderRequest(orderItemRequest);
    }

    public void assignOrdererId(Long ordererId) {
        this.ordererId = ordererId;
    }
}
