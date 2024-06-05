package com.kata.orderinhexagonal.discount.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<DiscountEntity, Long> {
}
