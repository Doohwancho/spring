package com.kata.orderinhexagonal.payment.adapter.out.persistence;

import com.kata.orderinhexagonal.order.adapter.out.persistence.FindOrderAdapter;
import com.kata.orderinhexagonal.order.adapter.out.persistence.OrderEntity;
import com.kata.orderinhexagonal.order.adapter.out.persistence.OrderStatusChangeAdapter;
import com.kata.orderinhexagonal.order.domain.Order;
import com.kata.orderinhexagonal.payment.application.port.out.PaymentSavePort;
import com.kata.orderinhexagonal.payment.domain.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class SavePaymentAdapter implements PaymentSavePort {

    private final OrderStatusChangeAdapter orderStatusChangeAdapter;
    private final FindOrderAdapter findOrderAdapter;

    private final PaymentRepository paymentRepository;

    @Override
    @Transactional
    public void save(Payment payment) {
        //step1) change order status
        Order order = payment.getOrder();
        orderStatusChangeAdapter.changeStatus(order);
        OrderEntity orderEntity = findOrderAdapter.loadOrderEntity(order.getId());

        //step2) save payment
        PaymentEntity paymentEntity = new PaymentEntity(orderEntity, payment.getCardNumber(), payment.getCardCvc(), order.getTotalPrice(), payment.getPaymentType(), payment.getCardType(), payment.getCardCompany(), payment.getStatus());
        paymentRepository.save(paymentEntity);
        payment.assignId(paymentEntity.getId());
    }
}