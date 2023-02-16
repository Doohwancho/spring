package com.cho.basic.상속관계.tablePerClass전략;

import com.cho.basic.상속관계.join전략.Item;

import javax.persistence.Entity;

@Entity
public class Movie_2 extends Item {
    private String director;
    private String actor;
}
