package com.kata.orderinhexagonal.delivery.adapter.out.persistence;

import com.kata.orderinhexagonal.delivery.domain.Delivery;
import org.springframework.stereotype.Component;

@Component
public class DeliveryMapper {
        public DeliveryEntity toEntity(Delivery delivery) {
            return new DeliveryEntity(delivery.getId(), delivery.getStatus(), delivery.getLocation(), delivery.getCreatedDateTime());
        }
}
