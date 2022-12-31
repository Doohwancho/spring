package com.tdd.tddTest.service;

import com.tdd.tddTest.domain.product.Product;

public class ProductService {

    public Product getProduct() {
        return new Product("A001", "monitor");
    }
    public Product getProduct(String code, String type) {
        return new Product(code, type);
    }
}
