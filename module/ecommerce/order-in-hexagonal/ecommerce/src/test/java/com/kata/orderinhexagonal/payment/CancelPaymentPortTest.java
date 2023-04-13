package com.kata.orderinhexagonal.payment;

import com.kata.orderinhexagonal.fixture.MemberFixture;
import com.kata.orderinhexagonal.fixture.OrderFixture;
import com.kata.orderinhexagonal.fixture.PaymentFixture;
import com.kata.orderinhexagonal.member.domain.Member;
import com.kata.orderinhexagonal.order.application.port.out.CancelPaymentPort;
import com.kata.orderinhexagonal.order.domain.Order;
import com.kata.orderinhexagonal.payment.adapter.out.persistence.PaymentEntity;
import com.kata.orderinhexagonal.payment.domain.Payment;
import com.kata.orderinhexagonal.payment.domain.PaymentStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CancelPaymentPortTest {

    @Autowired
    MemberFixture memberFixture;

    @Autowired
    OrderFixture orderFixture;

    @Autowired
    PaymentFixture paymentFixture;
    @Autowired
    CancelPaymentPort cancelPaymentPort;

    @BeforeEach
    void setUp() {
        paymentFixture.clearPayment();
        orderFixture.clearOrder();
        memberFixture.clearMember();
    }

    @AfterEach
    void tearDown() {
        paymentFixture.clearPayment();
        orderFixture.clearOrder();
        memberFixture.clearMember();
    }



    @Test
    void 결제_취소() throws InterruptedException {
        // given
        Member member = memberFixture.createMember("조두환", "doohwancho1993@gmail.com", "김포시");
        Order order = orderFixture.createOrder(member.getId());
        Payment payment = paymentFixture.createPayment(order.getId());

        // when
        cancelPaymentPort.request(order);

        // then
        Thread.sleep(1000);
        PaymentEntity paymentEntity = paymentFixture.getPaymentEntity(payment.getId());
        Assertions.assertThat(paymentEntity.getStatus()).isEqualTo(PaymentStatus.CANCELLATION_REQUEST);
    }
}