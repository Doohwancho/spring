package com.kata.orderinhexagonal.delivery.adapter.out.persistence;

import com.kata.orderinhexagonal.delivery.application.port.out.SaveDeliveryPort;
import com.kata.orderinhexagonal.delivery.domain.Delivery;
import com.kata.orderinhexagonal.discount.adapter.out.persistence.DiscountMapper;
import com.kata.orderinhexagonal.order.adapter.out.persistence.OrderEntity;
import com.kata.orderinhexagonal.order.adapter.out.persistence.OrderMapper;
import com.kata.orderinhexagonal.order.adapter.out.persistence.OrderStatusChangeAdapter;
import com.kata.orderinhexagonal.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@RequiredArgsConstructor
public class CreateDeliveryAdapter implements SaveDeliveryPort {

    private final DeliveryRepository deliveryRepository;
    private final OrderDeliveryRepository orderDeliveryRepository;
    private final DeliveryMapper deliveryMapper;
    private final OrderStatusChangeAdapter orderStatusChangeAdapter;

    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public void save(Delivery delivery) {
        //Goal
        //save delivery and orderDelivery

        //step1) delivery를 저장한다.
        DeliveryEntity deliveryEntity = deliveryMapper.toEntity(delivery);
        deliveryRepository.save(deliveryEntity);
        delivery.assignId(deliveryEntity.getId());

        //step2) orderDelivery를 저장하기 전, order status를 배송중으로 변경한다.
        Order order = delivery.getOrder();
        orderStatusChangeAdapter.changeStatus(order);

        //step3) orderDelivery를 저장한다.
//        OrderEntity orderEntity = findOrderAdapter.loadOrderEntity(order.getId());
        OrderEntity orderEntity = orderMapper.toEntity(order); //위에 대신 바로 entity로 변환.
        OrderDeliveryEntity orderDeliveryEntity = new OrderDeliveryEntity(orderEntity, deliveryEntity);
        orderDeliveryRepository.save(orderDeliveryEntity);
    }
}
