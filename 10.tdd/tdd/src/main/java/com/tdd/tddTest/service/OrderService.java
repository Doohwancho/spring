package com.tdd.tddTest.service;

import com.tdd.tddTest.domain.product.Product;
import com.tdd.tddTest.domain.user.User;

public class OrderService {
    private UserService userService;
    private ProductService productService;

    public OrderService(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    public User getUser() {
        return userService.getUser();
    }

    public Product getProduct() {
        return productService.getProduct();
    }
}
