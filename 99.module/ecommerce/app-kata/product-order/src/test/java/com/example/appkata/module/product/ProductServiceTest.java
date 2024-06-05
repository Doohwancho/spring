package com.example.appkata.module.product;

import com.example.appkata.module.product.application.ProductService;
import com.example.appkata.module.product.application.dto.CreateProductRequest;
import com.example.appkata.module.product.application.dto.UpdateProductRequest;
import com.example.appkata.module.product.domain.Product;
import com.example.appkata.module.product.infra.MemoryProductRepository;
import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.example.appkata.module.product")
@AllArgsConstructor
@SpringBootApplication
public class ProductServiceTest {

    private final ProductService productService;

    public ProductServiceTest() {
        this.productService = new ProductService(new MemoryProductRepository());
    }

    @Test
    @DisplayName("상품 등록")
    void create_product_test() {
        // given
        String productName = "노트북";
        int price = 1_000_000;
        CreateProductRequest request = CreateProductRequest.of(productName, price);
        // when
        Product product = productService.createProduct(request);
        // then
        Assertions.assertThat(product.getId()).isPositive();
        Assertions.assertThat(product.getName()).isEqualTo(productName);
        Assertions.assertThat(product.getPrice()).isEqualTo(price);
    }

    @Test
    @DisplayName("상품 수정")
    void update_product_test() {
        // given
        String productName = "노트북";
        int price = 1_000_000;
        CreateProductRequest request = CreateProductRequest.of(productName, price);
        Product product = productService.createProduct(request);

        String newProductName = "노트북2";
        int newPrice = 1_000_001;
        UpdateProductRequest updateRequest = UpdateProductRequest.of(product.getId(), newProductName, newPrice);
        // when
        productService.updateProduct(updateRequest);
        // then
        Assertions.assertThat(product.getName()).isEqualTo(newProductName);
        Assertions.assertThat(product.getPrice()).isEqualTo(newPrice);
    }

    @Test
    @DisplayName("상품 조회")
    void get_product_test() {
        // given
        String productName = "노트북";
        int price = 1_000_000;
        CreateProductRequest request = CreateProductRequest.of(productName, price);
        Product product = productService.createProduct(request);

        // when
        Product findProduct = productService.findProduct(product.getId());

        // then
        Assertions.assertThat(findProduct.getId()).isEqualTo(product.getId());
        Assertions.assertThat(findProduct.getName()).isEqualTo(productName);
        Assertions.assertThat(findProduct.getPrice()).isEqualTo(price);
    }
}
