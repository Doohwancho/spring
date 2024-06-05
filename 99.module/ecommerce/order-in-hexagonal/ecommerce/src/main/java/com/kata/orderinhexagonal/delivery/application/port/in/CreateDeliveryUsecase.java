package com.kata.orderinhexagonal.delivery.application.port.in;

import com.kata.orderinhexagonal.delivery.domain.Delivery;

public interface CreateDeliveryUsecase {
    Delivery create(DeliveryRequest request);
}
