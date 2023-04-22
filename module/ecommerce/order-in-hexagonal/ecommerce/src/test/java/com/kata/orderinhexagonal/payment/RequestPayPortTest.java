package com.kata.orderinhexagonal.payment;

import com.kata.orderinhexagonal.fixture.MemberFixture;
import com.kata.orderinhexagonal.fixture.OrderFixture;
import com.kata.orderinhexagonal.member.domain.Member;
import com.kata.orderinhexagonal.order.domain.Order;
import com.kata.orderinhexagonal.payment.application.port.out.RequestPay;
import com.kata.orderinhexagonal.payment.application.port.out.RequestPayPort;
import com.kata.orderinhexagonal.payment.domain.CardCompany;
import com.kata.orderinhexagonal.payment.domain.CardType;
import com.kata.orderinhexagonal.payment.domain.PaymentStatus;
import com.kata.orderinhexagonal.payment.domain.PaymentType;
import com.kata.orderinhexagonal.util.TestConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

public class RequestPayPortTest extends TestConfig {
    @Autowired
    MemberFixture memberFixture;
    @Autowired
    OrderFixture orderFixture;
    @Autowired
    RequestPayPort requestPayPort;


    @Test
    void requestPayTest() {
        // given
        Member member = memberFixture.createMember("조두환", "doohwancho1993@gmail.com", "김포시");
        Order order = orderFixture.createOrder(member.getId());

        CardType cardType = CardType.CREDIT_CARD;
        CardCompany cardCompany = CardCompany.KATA;
        String cardNumber = "1234567890123456";
        String cardCvc = "123";
        int paymentPrice = order.getTotalPrice();
        PaymentType paymentType = PaymentType.PAY_IN_FULL;
        RequestPay requestPay = RequestPay.of(cardType, cardCompany, cardNumber, cardCvc, paymentPrice, paymentType);
        // when
        PaymentStatus resultPaymentStatus = requestPayPort.pay(requestPay);

        // then
        Assertions.assertThat(resultPaymentStatus).isEqualTo(PaymentStatus.OK);
    }

}
