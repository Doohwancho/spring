package com.tdd.tddTest.dto.car;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CarDto {

    private long id;
    private String carName;
    private int doors;

    @Builder
    public CarDto(long id, String carName, int doors) {
        this.id = id;
        this.carName = carName;
        this.doors = doors;
    }
}
