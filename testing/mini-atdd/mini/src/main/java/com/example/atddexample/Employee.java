package com.example.atddexample;

import lombok.Getter;

/**
 * case2) service -> domain 의존관계
 */
@Getter
class Employee {
    Long id;
    String lastName;

    protected Employee(String lastName) {
        this.lastName = lastName;
    }
}
