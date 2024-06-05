package com.kata.orderinhexagonal.delivery.domain;

import com.kata.orderinhexagonal.order.domain.Order;

import java.time.LocalDateTime;

public class Delivery {
    private Long id;
    private DeliveryStatus status;
    private String location;
    private LocalDateTime createdDateTime;
    private Order order;

    public Delivery(Order order, DeliveryStatus deliveryStatus, String location) {
        this.order = order;
        this.status = deliveryStatus;
        this.location = location;
        this.createdDateTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public DeliveryStatus getStatus() {
        return status;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getCreatedDateTime() {
        return createdDateTime;
    }

    public void assignId(Long id) {
        this.id = id;
    }
}
