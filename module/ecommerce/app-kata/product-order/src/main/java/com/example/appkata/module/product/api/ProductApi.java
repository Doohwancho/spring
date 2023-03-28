package com.example.appkata.module.product.api;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import com.example.appkata.module.product.application.ProductService;
import com.example.appkata.module.product.application.dto.*;
import com.example.appkata.module.product.domain.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.example.appkata.common.config.CacheConfig.AppCacheTypeConstant.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductApi {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateProductResponse create(@RequestBody CreateProductRequest request) {
        Product product = productService.createProduct(request);
        return new CreateProductResponse(product);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @CachePut(value = FIND_PRODUCT_CACHE, key = "#request?.id")
    public UpdateProductResponse updateProduct(@RequestBody UpdateProductRequest request) {
        Product updateProduct = productService.updateProduct(request);
        return new UpdateProductResponse(updateProduct);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Cacheable(value = FIND_PRODUCT_CACHE, key = "#id")
    public FindProductResponse getProduct(@PathVariable("id") Long id) {
        Product product = productService.findProduct(id);
        return new FindProductResponse(product);
    }
}
