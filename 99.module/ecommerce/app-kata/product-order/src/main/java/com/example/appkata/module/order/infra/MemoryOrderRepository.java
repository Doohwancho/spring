package com.example.appkata.module.order.infra;

import com.example.appkata.module.order.domain.Order;
import com.example.appkata.module.order.domain.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MemoryOrderRepository implements OrderRepository {

    private final Map<Long, Order> persistMap = new ConcurrentHashMap<>();
    private final AtomicLong sequence = new AtomicLong(1);

    @Override
    public void save(Order order) {
        order.assignId(nextId());
        persistMap.put(order.getId(), order);
    }

    @Override
    public Optional<Order> findById(long orderId) {
        return Optional.ofNullable(persistMap.get(orderId));
    }

    private long nextId() {
        return sequence.getAndIncrement();
    }
}
