package com.kata.orderinhexagonal.delivery.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDeliveryRepository extends JpaRepository<OrderDeliveryEntity, Long> {
    @Query("SELECT d FROM OrderDeliveryEntity d join fetch d.order join fetch d.delivery WHERE d.order.id = ?1")
    List<OrderDeliveryEntity> findByOrderId(Long orderId);
}
