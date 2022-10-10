package com.tdd.tddTest.domain.car;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name="cars")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Car {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id; // Each car will be given an auto-generated unique identifier when stored

    @Column(name="car_name", nullable=false)
    private String carName; // We will also save the name of the car

    @Column(name="doors", nullable=false)
    private int doors; // We will also save the number of doors that a car has

    // getters and setters
}