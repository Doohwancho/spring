package com.example.appkata.module.product.domain;


import java.util.Optional;

public interface ProductRepository {
    void save(Product product);

    Optional<Product> findById(Long id);
}
