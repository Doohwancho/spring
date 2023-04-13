package com.kata.orderinhexagonal.delivery.application.port.in;

import com.kata.orderinhexagonal.delivery.domain.Delivery;
import com.kata.orderinhexagonal.delivery.domain.DeliveryStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryResponse {
    private Long id;
    private DeliveryStatus status;
    private String location;
    private LocalDateTime createdDateTime;
    private Long orderId;

    public DeliveryResponse(Delivery delivery) {
        this.id = delivery.getId();
        this.status = delivery.getStatus();
        this.location = delivery.getLocation();
        this.createdDateTime = delivery.getCreatedDateTime();
        this.orderId = delivery.getOrder().getId();
    }
}