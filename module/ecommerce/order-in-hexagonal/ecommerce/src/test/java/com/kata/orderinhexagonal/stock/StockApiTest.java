package com.kata.orderinhexagonal.stock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kata.orderinhexagonal.fixture.ItemFixture;
import com.kata.orderinhexagonal.fixture.StockFixture;
import com.kata.orderinhexagonal.item.domain.Item;
import com.kata.orderinhexagonal.stock.application.port.in.StockInRequest;
import com.kata.orderinhexagonal.stock.application.port.in.StockInResponse;
import com.kata.orderinhexagonal.stock.application.port.in.StockOutRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class StockApiTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ItemFixture itemFixture;

    @Autowired
    StockFixture stockFixture;

    @Test
    void 상품_입고() throws Exception {
        //given
        String name = "노트북";
        int price = 100000;
        Item item = itemFixture.createItem(name, price);

        int quantity = 10;
        StockInRequest stockInRequest = new StockInRequest(item.getId(), quantity);

        //when
        MockHttpServletResponse response = mockMvc.perform(post("/stock/in")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(stockInRequest)))
                .andReturn().getResponse();

        //then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        StockInResponse stockInResponse = objectMapper.readValue(response.getContentAsString(), StockInResponse.class);
        Assertions.assertThat(stockInResponse.getItemId()).isEqualTo(item.getId());
        Assertions.assertThat(stockInResponse.getItemName()).isEqualTo(name);
        Assertions.assertThat(stockInResponse.getQuantity()).isEqualTo(quantity);
    }


    @Test
    void 상품_출고() throws Exception {
        //given
        String name = "노트북";
        int price = 100000;
        Item item = itemFixture.createItem(name, price);

        int stockInQuantity = 10;
        stockFixture.stockIn(item, stockInQuantity);

        int stockOutQuantity = 5;


        StockOutRequest stockOutRequest = new StockOutRequest(item.getId(), stockOutQuantity);

        //when
        MockHttpServletResponse response = mockMvc.perform(post("/stock/out")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(stockOutRequest)))
                .andReturn().getResponse();

        //then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        StockInResponse stockOutResponse = objectMapper.readValue(response.getContentAsString(), StockInResponse.class);
        Assertions.assertThat(stockOutResponse.getItemId()).isEqualTo(item.getId());
        Assertions.assertThat(stockOutResponse.getItemName()).isEqualTo(name);
        Assertions.assertThat(stockOutResponse.getQuantity()).isEqualTo(stockInQuantity - stockOutQuantity);
    }
}
