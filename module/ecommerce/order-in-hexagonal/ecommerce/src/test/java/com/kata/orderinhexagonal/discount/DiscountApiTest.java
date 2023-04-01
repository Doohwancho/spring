package com.kata.orderinhexagonal.discount;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kata.orderinhexagonal.discount.application.port.in.ItemDiscountRequest;
import com.kata.orderinhexagonal.discount.application.port.in.ItemDiscountResponse;
import com.kata.orderinhexagonal.discount.domain.DiscountType;
import com.kata.orderinhexagonal.fixture.ItemFixture;
import com.kata.orderinhexagonal.item.domain.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class DiscountApiTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ItemFixture itemFixture;

    @Test
    void 상품_할인_등록_IN_PERCENTAGE() throws Exception {
        //given
        String name = "item1";
        int price = 1000;
        Item item = itemFixture.createItem(name, price);

        DiscountType discountType = DiscountType.PERCENTAGE;
        int discountRate = 10;
        ItemDiscountRequest itemDiscountRequest = ItemDiscountRequest.of(item.getId(), discountType, discountRate);

        //when
        MockHttpServletResponse response = mockMvc.perform(post("/discounts/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemDiscountRequest)))
                .andReturn().getResponse();

        //then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        ItemDiscountResponse itemDiscountResponse = objectMapper.readValue(response.getContentAsString(), ItemDiscountResponse.class);
        Assertions.assertThat(itemDiscountResponse.getItemId()).isEqualTo(item.getId());
        Assertions.assertThat(itemDiscountResponse.getDiscountType()).isEqualTo(discountType);
        Assertions.assertThat(itemDiscountResponse.getDiscountRate()).isEqualTo(discountRate);
    }


    @Test
    void 상품_할인_등록_IN_AMOUNT() throws Exception {
        //give
        String name = "item1";
        int price = 1000;
        Item item = itemFixture.createItem(name, price);

        DiscountType discountType = DiscountType.AMOUNT;
        int discountRate = 10;
        ItemDiscountRequest itemDiscountRequest = ItemDiscountRequest.of(item.getId(), discountType, discountRate);

        //when
        MockHttpServletResponse response = mockMvc.perform(post("/discounts/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(itemDiscountRequest)))
                .andReturn().getResponse();

        //then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        ItemDiscountResponse itemDiscountResponse = objectMapper.readValue(response.getContentAsString(), ItemDiscountResponse.class);
        Assertions.assertThat(itemDiscountResponse.getItemId()).isEqualTo(item.getId());
        Assertions.assertThat(itemDiscountResponse.getDiscountType()).isEqualTo(discountType);
        Assertions.assertThat(itemDiscountResponse.getDiscountRate()).isEqualTo(discountRate);
    }
}
