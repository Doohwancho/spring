package com.cho.basic.상속관계.join전략;

import javax.persistence.Entity;

@Entity
public class Movie extends Item {
    private String director;
    private String actor;
}

