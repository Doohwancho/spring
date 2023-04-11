package com.kata.orderinhexagonal.order;

import com.kata.orderinhexagonal.fixture.MemberFixture;
import com.kata.orderinhexagonal.fixture.OrderFixture;
import com.kata.orderinhexagonal.item.domain.Item;
import com.kata.orderinhexagonal.member.domain.Member;
import com.kata.orderinhexagonal.order.application.port.out.CancelStockOutItemPort;
import com.kata.orderinhexagonal.order.domain.Order;
import com.kata.orderinhexagonal.order.domain.OrderItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CancelStockOutItemPortTest {


    @Autowired
    OrderFixture orderFixture;

    @Autowired
    MemberFixture memberFixture;
    @Autowired
    CancelStockOutItemPort cancelStockOutItemPort;

    @BeforeEach
    void setUp() {
        orderFixture.clearOrder();
        memberFixture.clearMember();
    }

    @AfterEach
    void tearDown() {
        orderFixture.clearOrder();
        memberFixture.clearMember();
    }


    @Test
    void 상품_출고_취소() {
        // given
        Member member = memberFixture.createMember("조두환", "doohwancho@gmail.com", "김포시");
        Order order = orderFixture.createOrder(member.getId());
        OrderItem orderItem = order.getOrderItems().get(0);
        Item cancelOrderItem = orderItem.getItem();
        Integer currentStockQuantity = cancelOrderItem.getStockQuantity();

        // when
        int orderQuantity = orderItem.getOrderQuantity();
        cancelStockOutItemPort.cancelStockOutItem(cancelOrderItem, orderQuantity);

        // then
        Assertions.assertThat(cancelOrderItem.getStockQuantity()).isEqualTo(orderQuantity + currentStockQuantity);
        Assertions.assertThat(cancelOrderItem.getStockQuantity()).isEqualTo(10);
    }

}
