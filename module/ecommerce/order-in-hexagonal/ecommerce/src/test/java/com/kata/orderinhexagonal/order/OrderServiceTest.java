package com.kata.orderinhexagonal.order;

import com.kata.orderinhexagonal.fixture.ItemFixture;
import com.kata.orderinhexagonal.fixture.MemberFixture;
import com.kata.orderinhexagonal.fixture.OrderFixture;
import com.kata.orderinhexagonal.fixture.StockFixture;
import com.kata.orderinhexagonal.item.adapter.out.persistence.ItemEntity;
import com.kata.orderinhexagonal.item.domain.Item;
import com.kata.orderinhexagonal.member.domain.Member;
import com.kata.orderinhexagonal.order.adapter.out.persistence.OrderEntity;
import com.kata.orderinhexagonal.order.adapter.out.persistence.OrderItemEntity;
import com.kata.orderinhexagonal.order.application.port.in.CancelOrderRequest;
import com.kata.orderinhexagonal.order.application.port.in.CreateOrderRequest;
import com.kata.orderinhexagonal.order.application.port.in.OrderItemRequest;
import com.kata.orderinhexagonal.order.application.service.OrderService;
import com.kata.orderinhexagonal.order.domain.Order;
import com.kata.orderinhexagonal.order.domain.OrderStatus;
import com.kata.orderinhexagonal.stock.domain.Stock;
import com.kata.orderinhexagonal.util.TestConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceTest extends TestConfig {

    @Autowired
    MemberFixture memberFixture;
    @Autowired
    ItemFixture itemFixture;
    @Autowired
    StockFixture stockFixture;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderFixture orderFixture;

    @Test
    @Transactional
    void save_order_test() throws InterruptedException {
        //given
        int item1Price = 10000;
        int item1StockQuantity = 10;
        int item1OrderQuantity = 1;

        int item2Price = 20000;
        int item2StockQuantity = 20;
        int item2OrderQuantity = 2;

        Member orderer = memberFixture.createMember("member1", "email@gmail.com", "gimpo");

        Item item1 = itemFixture.createItem("item1", item1Price);
        Stock stock1 = stockFixture.stockIn(item1, item1StockQuantity);

        Item item2 = itemFixture.createItem("item2", item2Price);
        Stock stock2 = stockFixture.stockIn(item2, item2StockQuantity);

        List<OrderItemRequest> orderItemRequests = new ArrayList<>();
        orderItemRequests.add(OrderItemRequest.of(item1.getId(), item1OrderQuantity));
        orderItemRequests.add(OrderItemRequest.of(item2.getId(), item2OrderQuantity));

        CreateOrderRequest request = CreateOrderRequest.of(orderItemRequests);
        request.assignOrdererId(orderer.getId());


        //when
        Order result = orderService.createOrder(request);


        //then
        Thread.sleep(1000); //wait 1 second for stock quantity to asynchrously update

        OrderEntity resultOrderEntity = orderFixture.getOrderEntity(result.getId());
        Assertions.assertThat(resultOrderEntity.getMember().getId()).isEqualTo(orderer.getId());
        Assertions.assertThat(resultOrderEntity.getStatus()).isEqualTo(OrderStatus.NOT_PAYED);

        OrderItemEntity resultItemEntity1 = resultOrderEntity.getOrderItems().get(0);
        OrderItemEntity resultItemEntity2 = resultOrderEntity.getOrderItems().get(1);

        Assertions.assertThat(resultItemEntity1.getOrderPrice()).isEqualTo(item1Price);
        Assertions.assertThat(resultItemEntity1.getOrderQuantity()).isEqualTo(item1OrderQuantity);

        Assertions.assertThat(resultItemEntity2.getOrderPrice()).isEqualTo(item2Price);
        Assertions.assertThat(resultItemEntity2.getOrderQuantity()).isEqualTo(item2OrderQuantity);

        ItemEntity resultItem1 = resultItemEntity1.getItem();
        ItemEntity resultItem2 = resultItemEntity2.getItem();

        Assertions.assertThat(resultItem1.getId()).isEqualTo(item1.getId());
        Assertions.assertThat(resultItem1.getName()).isEqualTo(item1.getName());
        Assertions.assertThat(resultItem1.getPrice()).isEqualTo(item1.getPrice());
        Assertions.assertThat(resultItem1.getStockQuantity()).isEqualTo(item1StockQuantity - item1OrderQuantity); //stock quantity should be updated

        Assertions.assertThat(resultItem2.getId()).isEqualTo(item2.getId());
        Assertions.assertThat(resultItem2.getName()).isEqualTo(item2.getName());
        Assertions.assertThat(resultItem2.getPrice()).isEqualTo(item2.getPrice());
        Assertions.assertThat(resultItem2.getStockQuantity()).isEqualTo(item2StockQuantity - item2OrderQuantity); //stock quantity should be updated
    }


    @Test
    @Transactional
    void cancel_order_test() {
        //given
        Member member = memberFixture.createMember("member1", "email@gmail.com", "gimpo");
        Order order = orderFixture.createOrder(member.getId());

        CancelOrderRequest request = CancelOrderRequest.of(order.getId());
        request.assignOrdererId(member.getId());

        //when
        Order cancelOrder = orderService.cancelOrder(request);

        //then
        Assertions.assertThat(cancelOrder.getStatus()).isEqualTo(OrderStatus.CANCELED);
        OrderEntity orderEntity = orderFixture.getOrderEntity(cancelOrder.getId());
        Assertions.assertThat(orderEntity.getStatus()).isEqualTo(OrderStatus.CANCELED);
    }
}
