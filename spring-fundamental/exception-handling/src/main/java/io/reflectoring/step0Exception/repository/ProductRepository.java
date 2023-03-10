package io.reflectoring.step0Exception.repository;

import io.reflectoring.step0Exception.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}

