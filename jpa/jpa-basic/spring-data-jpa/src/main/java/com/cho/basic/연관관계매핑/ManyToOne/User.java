package com.cho.basic.연관관계매핑.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
public class User {
//    @Id
//    @Column(name="seq")
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    private int id;

    @Id
    @Column(name="seq")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int seq;

    @Column(name="name")
    private String name;

    public User(String name){
        this.name = name;
    }
}
