package com.kata.orderinhexagonal.order.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long> {

    //TODO c-b-6-10.orderItem을 repository로 찾을 때, item과 order를 fetch join하여 찾는다.
    @Query("select oi from OrderItemEntity oi join fetch oi.item join fetch oi.order where oi.order.id = ?1")
    List<OrderItemEntity> findByOrderId(Long orderId);
}
