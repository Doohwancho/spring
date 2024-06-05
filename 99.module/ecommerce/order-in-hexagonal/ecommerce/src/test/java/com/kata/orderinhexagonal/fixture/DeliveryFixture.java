package com.kata.orderinhexagonal.fixture;

import com.kata.orderinhexagonal.delivery.adapter.out.persistence.DeliveryEntity;
import com.kata.orderinhexagonal.delivery.adapter.out.persistence.DeliveryRepository;
import com.kata.orderinhexagonal.delivery.adapter.out.persistence.OrderDeliveryEntity;
import com.kata.orderinhexagonal.delivery.adapter.out.persistence.OrderDeliveryRepository;
import com.kata.orderinhexagonal.util.TestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeliveryFixture {

    @Autowired
    DeliveryRepository deliveryRepository;

    @Autowired
    OrderDeliveryRepository orderDeliveryRepository;

    public DeliveryEntity getDeliveryEntity(Long deliveryId) {
        return deliveryRepository.findById(deliveryId).get();
    }

    public List<OrderDeliveryEntity> getOrderDeliveryList(Long orderId) {
        return orderDeliveryRepository.findByOrderId(orderId);
    }

    public void clearDelivery() {
        orderDeliveryRepository.deleteAll();
        deliveryRepository.deleteAll();
    }
}
