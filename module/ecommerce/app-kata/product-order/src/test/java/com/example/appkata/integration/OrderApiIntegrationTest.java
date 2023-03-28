package com.example.appkata.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.example.appkata.fixture.OrderFixture;
import com.example.appkata.fixture.ProductFixture;
import com.example.appkata.module.order.application.dto.CreateOrderRequest;
import com.example.appkata.module.order.application.dto.CreateOrderResponse;
import com.example.appkata.module.order.application.dto.FindOrderResponse;
import com.example.appkata.module.order.domain.Order;
import com.example.appkata.module.product.domain.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class OrderApiIntegrationTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @Autowired ProductFixture productFixture;
    @Autowired
    OrderFixture orderFixture;

    @Test
    void 주문_요청() throws Exception {
        // given
        Product product = productFixture.createProduct("상품1", 100);
        int quantity = 10;
        CreateOrderRequest request = CreateOrderRequest.of(product.getId(),quantity);
        int expectedTotalPrice = product.getPrice() * quantity;

        // when
        MockHttpServletResponse response = mockMvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andReturn().getResponse();

        // then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        CreateOrderResponse createOrderResponse = objectMapper.readValue(response.getContentAsString(),
                CreateOrderResponse.class);
        Assertions.assertThat(createOrderResponse.getOrderId()).isPositive();
        Assertions.assertThat(createOrderResponse.getQuantity()).isEqualTo(quantity);
        Assertions.assertThat(createOrderResponse.getTotalPrice()).isEqualTo(expectedTotalPrice);
        Assertions.assertThat(createOrderResponse.getProductId()).isEqualTo(product.getId());
        Assertions.assertThat(createOrderResponse.getProductName()).isEqualTo(product.getName());
    }

    @Test
    void 주문_조회() throws Exception{
        // given
        Product product = productFixture.createProduct("상품1", 100);
        Order order = orderFixture.createOrder(product, 10);

        Long orderId = order.getId();
        Product orderProduct = order.getProduct();

        // when
        MockHttpServletResponse response = mockMvc.perform(get("/orders/{orderId}", orderId)).andReturn().getResponse();

        // then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        FindOrderResponse findOrderResponse = objectMapper.readValue(response.getContentAsString(),
                FindOrderResponse.class);
        Assertions.assertThat(findOrderResponse.getId()).isEqualTo(orderId);
        Assertions.assertThat(findOrderResponse.getProductId()).isEqualTo(orderProduct.getId());
        Assertions.assertThat(findOrderResponse.getProductName()).isEqualTo(orderProduct.getName());
        Assertions.assertThat(findOrderResponse.getTotalPrice()).isEqualTo(order.getTotalPrice());
        Assertions.assertThat(findOrderResponse.getQuantity()).isEqualTo(order.getQuantity());
    }

    @Test
    void 주문_취소() {
        // given

        // when

        // then
    }


}
