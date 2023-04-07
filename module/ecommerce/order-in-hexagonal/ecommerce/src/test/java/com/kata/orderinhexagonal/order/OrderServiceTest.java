package com.kata.orderinhexagonal.order;

import com.kata.orderinhexagonal.fixture.ItemFixture;
import com.kata.orderinhexagonal.fixture.MemberFixture;
import com.kata.orderinhexagonal.fixture.StockFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    MemberFixture memberFixture;

    @Autowired
    ItemFixture itemFixture;

    @Autowired
    StockFixture stockFixture;

    @Test
    void save_order_test() {
        //given


        //when
        saveOrderPort.save(order);

        //then
        OrderEntity orderEntity = orderFixture.getOrderEntity(order.getId());

    }
}
