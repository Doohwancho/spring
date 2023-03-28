package com.example.appkata.module.product.application;

import com.example.appkata.module.product.application.dto.CreateProductRequest;
import com.example.appkata.module.product.application.dto.UpdateProductRequest;
import com.example.appkata.module.product.domain.Product;
import com.example.appkata.module.product.domain.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product createProduct(CreateProductRequest request) {
        Product product = new Product(request.getProductName(), request.getPrice());
        productRepository.save(product);
        return product;
    }

    public Product updateProduct(UpdateProductRequest request) {
        Product product = findProduct(request.getId());
        product.update(request.getProductName(), request.getPrice());
        return product;
    }

    public Product findProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }
}
