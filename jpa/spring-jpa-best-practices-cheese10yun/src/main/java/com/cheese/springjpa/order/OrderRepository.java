package com.cheese.springjpa.order;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findById(long id);
}
