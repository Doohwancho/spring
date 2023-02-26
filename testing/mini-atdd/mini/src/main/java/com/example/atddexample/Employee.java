package com.example.atddexample;

/**
 * case2) service -> domain 의존관계
 */
class Employee {
    Long id;
    String lastName;

    public Employee(String lastName) {
        this.lastName = lastName;
    }
}
