package io.reflectoring.step0_Exception.controller;

import io.reflectoring.step0_Exception.entity.Product;
import io.reflectoring.step0_Exception.model.ProductInput;
import io.reflectoring.step0_Exception.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j(topic = "PRODUCT_CONTROLLER")
@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id){
        return productService.getProduct(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody @Valid ProductInput input){
        return productService.addProduct(input);
    }


}

