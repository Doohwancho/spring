package com.kata.orderinhexagonal.payment.adapter.out.persistence;

import com.kata.orderinhexagonal.payment.domain.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

    @Query("select p from PaymentEntity p where p.order.id = ?1")
    Optional<PaymentEntity> findByOrderId(Long orderId);

    @Query("select p from PaymentEntity p where p.status = ?1")
    List<PaymentEntity> findByCancellationStatus(PaymentStatus paymentStatus);
}
