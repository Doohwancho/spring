package com.kata.orderinhexagonal.delivery;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kata.orderinhexagonal.delivery.application.port.in.DeliveryRequest;
import com.kata.orderinhexagonal.delivery.application.port.in.DeliveryResponse;
import com.kata.orderinhexagonal.delivery.domain.DeliveryStatus;
import com.kata.orderinhexagonal.fixture.MemberFixture;
import com.kata.orderinhexagonal.fixture.OrderFixture;
import com.kata.orderinhexagonal.member.domain.Member;
import com.kata.orderinhexagonal.order.domain.Order;
import com.kata.orderinhexagonal.util.TestConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;

@AutoConfigureMockMvc
public class DeliveryApiTest extends TestConfig {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MemberFixture memberFixture;

    @Autowired
    OrderFixture orderFixture;


    @Test
    void 주문_배송_시작() throws Exception {
        // given
        Member member = memberFixture.createMember("조두환", "doohwancho1993@gmail.com", "김포시");
        Order order = orderFixture.createOrder(member.getId());

        String startedLocation = "야시장";
        DeliveryRequest request = DeliveryRequest.of(order.getId(), DeliveryStatus.SHIPPING, startedLocation);

        // when
        MockHttpServletResponse response = mockMvc.perform(post("/delivery")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andReturn().getResponse();

        // then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        DeliveryResponse deliveryResponse = objectMapper.readValue(response.getContentAsString(),
                DeliveryResponse.class);
        Assertions.assertThat(deliveryResponse.getId()).isPositive();
        Assertions.assertThat(deliveryResponse.getStatus()).isEqualTo(DeliveryStatus.SHIPPING);
        Assertions.assertThat(deliveryResponse.getLocation()).isEqualTo(startedLocation);
        Assertions.assertThat(deliveryResponse.getCreatedDateTime()).isNotNull();
        Assertions.assertThat(deliveryResponse.getOrderId()).isEqualTo(order.getId());
    }

}
