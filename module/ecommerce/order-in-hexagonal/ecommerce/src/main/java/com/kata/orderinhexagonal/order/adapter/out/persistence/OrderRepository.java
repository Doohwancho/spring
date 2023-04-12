package com.kata.orderinhexagonal.order.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    //TODO - c-b-6-9. order을 jpa repository로 찾을 때, member를 fetch join하여 찾는다.
    @Query("select o from OrderEntity o join fetch o.member where o.id = ?1") // = ?1도 된다.
    Optional<OrderEntity> findOrderWithMemberId(Long ordererId);
}
