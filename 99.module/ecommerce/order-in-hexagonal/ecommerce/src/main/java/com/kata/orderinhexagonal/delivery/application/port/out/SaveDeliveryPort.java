package com.kata.orderinhexagonal.delivery.application.port.out;

import com.kata.orderinhexagonal.delivery.domain.Delivery;

public interface SaveDeliveryPort {
    void save(Delivery delivery);
}
