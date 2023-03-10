package io.reflectoring.step0Exception.model;


import io.reflectoring.step0Exception.entity.Category;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductInput {

    @NotBlank
    private String name;

    @NotNull
    private Double price;

    private Double weight;

    private Category category;
}