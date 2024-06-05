package com.cho.annotation.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Board {

    private int index;
    private String writer;
    private String contents;

}
