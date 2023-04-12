package com.kata.orderinhexagonal.delivery.application.port.in;

import com.kata.orderinhexagonal.delivery.domain.DeliveryStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryRequest {
    @NotNull(message = "주문 번호를 입력해주세요.")
    private Long orderId;
    @NotNull(message = "배송 상태를 입력해주세요.")
    private DeliveryStatus deliveryStatus;
    @NotNull(message = "배송 위치를 입력해주세요.")
    private String location;

    public DeliveryRequest(Long orderId, DeliveryStatus deliveryStatus, String location) {
        this.orderId = orderId;
        this.deliveryStatus = deliveryStatus;
        this.location = location;
    }

    public static DeliveryRequest of(Long orderId, DeliveryStatus deliveryStatus, String location) {
        return new DeliveryRequest(orderId, deliveryStatus, location);
    }
}
