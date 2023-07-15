package com.tdd.tddTest.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class User {

    long id;
    String name;
    int age;
    int level;

    @Builder
    public User(long id, String name, int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int setUserLevel(int level){
        this.level = level;
        return level;
    }
}
