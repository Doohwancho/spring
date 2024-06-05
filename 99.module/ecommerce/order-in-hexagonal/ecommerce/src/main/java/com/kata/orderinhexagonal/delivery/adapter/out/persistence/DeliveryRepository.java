package com.kata.orderinhexagonal.delivery.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<DeliveryEntity, Long> {
}
