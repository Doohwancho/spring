package com.example.appkata.fixture;

import com.example.appkata.module.product.application.ProductService;
import com.example.appkata.module.product.application.dto.CreateProductRequest;
import com.example.appkata.module.product.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductFixture {

    private final ProductService productService;

    public ProductFixture(ProductService productService) {
        this.productService = productService;
    }

    public Product createProduct(String oldProductName, int oldPrice) {
        CreateProductRequest request = new CreateProductRequest(oldProductName, oldPrice);
        return productService.createProduct(request);
    }
}
