package com.tdd.tddTest.domain.product;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Product {

    String code;
    String type;

    //get serial method
    public String getSerial() {
        return code;
    }
}
