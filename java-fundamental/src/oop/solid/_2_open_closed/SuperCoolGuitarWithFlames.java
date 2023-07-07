package oop.solid._2_open_closed;

public class SuperCoolGuitarWithFlames extends Guitar {

    private String flameColor;

    //constructor, getters + setters
}

/*

1. Guitar가 먼저 있었고,
2. 나중에 guitar with flames 추가됨. 이 때, 기존 Guitar코드는 안건드리면서, 상속을 이용해서 그 위에 기능 덧 붙임. (implements 이용도 가능)

1. 여튼 새로운 기능을 외부에 추가해서 덧붙였으니 open for extension
2. 근데 그러면서 기존 코드 안바꿨으니까, no modification



 */