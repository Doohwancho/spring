package com.tdd.tddTest.domain.product;

import lombok.*;

@AllArgsConstructor
public class Product {

    String code;
    String type;

    //get serial method
    public String getSerial() {
        return code;
    }
}
