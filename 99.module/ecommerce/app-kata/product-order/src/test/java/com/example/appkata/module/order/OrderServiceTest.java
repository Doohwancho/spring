package com.example.appkata.module.order;

import com.example.appkata.fixture.ProductFixture;
import com.example.appkata.module.order.application.OrderService;
import com.example.appkata.module.order.application.dto.CreateOrderRequest;
import com.example.appkata.module.order.domain.Order;
import com.example.appkata.module.product.domain.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    ProductFixture productFixture;
    @Autowired
    OrderService orderService;

    @Test
    void 상품_주문() {
        // given
        Product product = productFixture.createProduct("상품1", 100);

        long productId = product.getId();
        int quantity = 10;
        int totalPrice = product.getPrice() * quantity;
        CreateOrderRequest request = CreateOrderRequest.of(productId, quantity);

        // when
        Order order = orderService.order(request);

        // then
        Assertions.assertThat(order.getId()).isPositive();
        Assertions.assertThat(order.getProduct().getId()).isEqualTo(productId);
        Assertions.assertThat(order.getProduct().getName()).isEqualTo(product.getName());
        Assertions.assertThat(order.getQuantity()).isEqualTo(quantity);
        Assertions.assertThat(order.getTotalPrice()).isEqualTo(totalPrice);

    }

    @Test
    void 주문_조회() {
        // given
        String productName = "상품1";
        int quantity = 10;
        int price = 1000;
        Product product = productFixture.createProduct(productName, price);
        long productId = product.getId();
        Order order = orderService.order(CreateOrderRequest.of(productId, quantity));
        long orderId = order.getId();
        Product orderProduct = order.getProduct();
        int totalPrice = order.getTotalPrice();

        // when
        Order findOrder = orderService.findOrder(orderId);

        // then
        Assertions.assertThat(findOrder.getId()).isEqualTo(orderId);
        Assertions.assertThat(findOrder.getTotalPrice()).isEqualTo(totalPrice);
        Assertions.assertThat(findOrder.getQuantity()).isEqualTo(quantity);
        Assertions.assertThat(orderProduct.getId()).isEqualTo(productId);
        Assertions.assertThat(orderProduct.getName()).isEqualTo(productName);
    }


}
