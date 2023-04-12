package com.kata.orderinhexagonal.delivery.application.service;

import com.kata.orderinhexagonal.delivery.application.port.in.DeliveryRequest;
import com.kata.orderinhexagonal.delivery.application.port.out.OrderDeliveryLoadPort;
import com.kata.orderinhexagonal.delivery.application.port.out.SaveDeliveryPort;
import com.kata.orderinhexagonal.delivery.domain.Delivery;
import com.kata.orderinhexagonal.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    private final SaveDeliveryPort saveDeliveryPort;

    private final OrderDeliveryLoadPort orderDeliveryPort;

    public Delivery create(DeliveryRequest request){
        //step1) orderId를 받아 주문을 load 한다.
        Order order = orderDeliveryPort.load(request.getOrderId());

        //step2) 주문의 상태를 배송중으로 변경한다.
        order.updateDeliveredStatus();

        //step3) 배송정보를 저장한다.
        Delivery delivery = new Delivery(order, request.getDeliveryStatus(), request.getLocation());
        saveDeliveryPort.save(delivery);
        return delivery;
    }
}
