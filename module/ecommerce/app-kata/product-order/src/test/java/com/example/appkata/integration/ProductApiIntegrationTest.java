package com.example.appkata.integration;


import com.example.appkata.fixture.ProductFixture;
import com.example.appkata.module.product.application.ProductService;
import com.example.appkata.module.product.application.dto.*;
import com.example.appkata.module.product.domain.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProductApiIntegrationTest {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductFixture productFixture;

    @SpyBean
    ProductService productService;

    @Test
    void 상품_등록_요청() throws Exception {
        // given
        String productName = "노트북";
        int price = 1_000_000;
        CreateProductRequest request = CreateProductRequest.of(productName, price);

        // when
        MockHttpServletResponse response = mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andReturn().getResponse();

        // then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        CreateProductResponse createProductResponse = objectMapper.readValue(response.getContentAsString(),
                CreateProductResponse.class);
        Assertions.assertThat(createProductResponse.getId()).isPositive();
        Assertions.assertThat(createProductResponse.getProductName()).isEqualTo(productName);
        Assertions.assertThat(createProductResponse.getPrice()).isEqualTo(price);
    }

    @Test
    void 상품_수정_요청() throws Exception {
        // given
        String oldProductName = "노트북";
        int oldPrice = 1_000_000;
        Product product = productFixture.createProduct(oldProductName, oldPrice);

        Long productId = product.getId();
        String newProductName = "노트북2";
        int newPrice = 2_000_000;
        UpdateProductRequest request = UpdateProductRequest.of(productId, newProductName, newPrice);

        // when
        MockHttpServletResponse response = mockMvc.perform(put("/products/{id}", productId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andReturn().getResponse();

        // then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        UpdateProductResponse updateProductResponse = objectMapper.readValue(response.getContentAsString(), UpdateProductResponse.class);
        Assertions.assertThat(updateProductResponse.getId()).isEqualTo(productId);
        Assertions.assertThat(updateProductResponse.getProductName()).isEqualTo(newProductName);
        Assertions.assertThat(updateProductResponse.getPrice()).isEqualTo(newPrice);
    }

    @Test
    void 상품_조회_요청() throws Exception {
        // given
        Product product = productFixture.createProduct("노트북", 1_000_000);

        // when
        MockHttpServletResponse response = mockMvc.perform(get("/products/{id}", product.getId())
        ).andReturn().getResponse();

        // then
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        FindProductResponse findProductResponse = objectMapper.readValue(response.getContentAsString(), FindProductResponse.class);
        Assertions.assertThat(findProductResponse.getId()).isEqualTo(product.getId());
        Assertions.assertThat(findProductResponse.getProductName()).isEqualTo(product.getName());
        Assertions.assertThat(findProductResponse.getPrice()).isEqualTo(product.getPrice());
    }


    @Test
    void 상품_조회_요청_캐시_적용() throws Exception {
        // given
        Product product = productFixture.createProduct("노트북", 1_000_000);

        // when
        for (int i = 0; i < 10; i++) {
            mockMvc.perform(get("/products/{id}", product.getId())).andReturn();
        }

        // then
        verify(productService, times(1)).findProduct(product.getId()); //TODO - c-a-2-2. cache test code는 이런식으로 작성하는구나
    }

}