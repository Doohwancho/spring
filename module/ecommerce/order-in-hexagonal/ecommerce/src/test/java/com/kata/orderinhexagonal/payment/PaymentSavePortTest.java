package com.kata.orderinhexagonal.payment;


import com.kata.orderinhexagonal.fixture.MemberFixture;
import com.kata.orderinhexagonal.fixture.OrderFixture;
import com.kata.orderinhexagonal.fixture.PaymentFixture;
import com.kata.orderinhexagonal.member.domain.Member;
import com.kata.orderinhexagonal.order.domain.Order;
import com.kata.orderinhexagonal.payment.adapter.out.persistence.PaymentEntity;
import com.kata.orderinhexagonal.payment.application.port.out.PaymentSavePort;
import com.kata.orderinhexagonal.payment.domain.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PaymentSavePortTest {

    @Autowired
    MemberFixture memberFixture;

    @Autowired
    OrderFixture orderFixture;

    @Autowired
    PaymentSavePort paymentSavePort;
    @Autowired
    PaymentFixture paymentFixture;

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
    void 결제저장() {
        // given
        Member member = memberFixture.createMember("조두환", "doohwancho1993@gmail.com", "김포시");
        Order order = orderFixture.createOrder(member.getId());
        CardType cardType = CardType.CREDIT_CARD;
        CardCompany cardCompany = CardCompany.KATA;
        PaymentType paymentType = PaymentType.PAY_IN_FULL;
        String cardNumber = "1234567890123456";
        String cardCvc = "123";
        Payment payment = new Payment(order, paymentType, cardType, cardCompany, cardNumber, cardCvc, PaymentStatus.OK);

        // when
        paymentSavePort.save(payment);

        // then
        Assertions.assertThat(payment.getId()).isPositive();
        Assertions.assertThat(payment.getStatus()).isEqualTo(PaymentStatus.OK);
        Assertions.assertThat(payment.getPaymentType()).isEqualTo(paymentType);
        Assertions.assertThat(payment.getCardType()).isEqualTo(cardType);
        Assertions.assertThat(payment.getCardCompany()).isEqualTo(cardCompany);
        Assertions.assertThat(payment.getCardNumber()).isEqualTo(cardNumber);
        Assertions.assertThat(payment.getCardCvc()).isEqualTo(cardCvc);
        PaymentEntity paymentEntity = paymentFixture.getPaymentEntity(payment.getId());
        Assertions.assertThat(paymentEntity.getId()).isEqualTo(payment.getId());
        Assertions.assertThat(paymentEntity.getPaymentPrice()).isEqualTo(payment.getOrder().getTotalPrice());
    }

}
