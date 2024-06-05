package com.kata.orderinhexagonal.item;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kata.orderinhexagonal.item.application.port.in.CreateItemRequest;
import com.kata.orderinhexagonal.item.application.port.in.CreateItemResponse;
import com.kata.orderinhexagonal.util.TestConfig;
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

@AutoConfigureMockMvc
public class ItemApiTest extends TestConfig {

    @Autowired
    MockMvc mockMvc;
    
    @Autowired
    ObjectMapper objectMapper;
    
    @Test 
    void 상품_등록() throws Exception {
        //given
        String name = "노트북";
        int price = 1_000_000;
        CreateItemRequest request = CreateItemRequest.of(name, price);
        
        //when
        MockHttpServletResponse response = mockMvc.perform(post("/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andReturn().getResponse();

        //then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        CreateItemResponse createItemResponse = objectMapper.readValue(response.getContentAsString(), CreateItemResponse.class);
        Assertions.assertThat(createItemResponse.getId()).isPositive();
        Assertions.assertThat(createItemResponse.getName()).isEqualTo(name);
        Assertions.assertThat(createItemResponse.getPrice()).isEqualTo(price);
        Assertions.assertThat(createItemResponse.getStockQuantity()).isZero();
    }

}
