package com.kata.orderinhexagonal.order;

import com.kata.orderinhexagonal.fixture.ItemFixture;
import com.kata.orderinhexagonal.fixture.MemberFixture;
import com.kata.orderinhexagonal.fixture.StockFixture;
import com.kata.orderinhexagonal.item.domain.Item;
import com.kata.orderinhexagonal.member.domain.Member;
import com.kata.orderinhexagonal.order.adapter.out.persistence.OrderEntity;
import com.kata.orderinhexagonal.order.application.port.in.CreateOrderRequest;
import com.kata.orderinhexagonal.order.application.port.in.OrderItemRequest;
import com.kata.orderinhexagonal.order.application.service.OrderService;
import com.kata.orderinhexagonal.order.domain.Order;
import com.kata.orderinhexagonal.order.domain.OrderItem;
import com.kata.orderinhexagonal.stock.domain.Stock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    MemberFixture memberFixture;

    @Autowired
    ItemFixture itemFixture;

    @Autowired
    StockFixture stockFixture;

    @Autowired
    OrderService orderService;

    @Test
    @Disabled
    void save_order_test() throws InterruptedException {
        //given
        int item1StockQuantity = 10;
        int item2StockQuantity = 20;
        int item1OrderQuantity = 1;
        int item2OrderQuantity = 2;

        Member member = memberFixture.createMember("member1", "email@gmail.com", "gimpo");

        Item item1 = itemFixture.createItem("item1", 10000);
        Stock stock1 = stockFixture.stockIn(item1, item1StockQuantity);

        Item item2 = itemFixture.createItem("item2", 20000);
        Stock stock2 = stockFixture.stockIn(item2, item2StockQuantity);

        List<OrderItemRequest> orderItemRequests = new ArrayList<>();
        orderItemRequests.add(OrderItemRequest.of(item1.getId(), item1OrderQuantity));
        orderItemRequests.add(OrderItemRequest.of(item2.getId(), item2OrderQuantity));

        CreateOrderRequest request = CreateOrderRequest.of(orderItemRequests);
        request.assignOrdererId(member.getId());


//        Member orderer = loadOrdererPort.load(request.getOrdererId());
//        Order order = new Order(orderer);
//        request.getOrderItemRequests().forEach(orderItemRequest -> {
//            Item item = loadOrderItemPort.loadItem(orderItemRequest.getItemId());
//            int orderPrice = item.getPrice() * orderItemRequest.getOrderQuantity();
//            order.addOrderItem(item, orderItemRequest.getOrderQuantity(), orderPrice);
//        });
//        List<OrderItem> orderItems = order.getOrderItems();
//        for (OrderItem orderItem : orderItems) {
//            itemOrderStockOutPort.stockOut(orderItem.getItem(), orderItem.getOrderQuantity());
//        }
//
//
//        Thread.sleep(1000);

        //when
        Order result = orderService.createOrder(request);

        //then
//        OrderEntity orderEntity = orderFixture.getOrderEntity(result.getId());
//        Assertions.assertThat(orderEntity.getMemberId()).isEqualTo(member.getId());
//
//        Item resultItem1 = orderEntity.getOrderItems().get(0).getItem();
//        Item resultItem2 = orderEntity.getOrderItems().get(1).getItem();
//
//        Assertions.assertThat(item1.getId()).isEqualTo(resultItem1.getId());
//        Assertions.assertThat(item1.getName()).isEqualTo(resultItem1.getName());
//        Assertions.assertThat(item1.getPrice()).isEqualTo(resultItem1.getPrice());
//        Assertions.assertThat(item1.getStockQuantity()).isEqualTo(item1StockQuantity - item1OrderQuantity);
//
//        Assertions.assertThat(item2.getId()).isEqualTo(resultItem2.getId());
//        Assertions.assertThat(item2.getName()).isEqualTo(resultItem2.getName());
//        Assertions.assertThat(item2.getPrice()).isEqualTo(resultItem2.getPrice());
//        Assertions.assertThat(item2.getStockQuantity()).isEqualTo(item2StockQuantity - item2OrderQuantity);
    }
}
