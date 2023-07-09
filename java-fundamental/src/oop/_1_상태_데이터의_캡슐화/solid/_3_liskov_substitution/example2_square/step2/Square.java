package oop._1_상태_데이터의_캡슐화.solid._3_liskov_substitution.example2_square.step2;

public class Square extends Shape {

    public Square(int length) {
        setWidth(length);
        setHeight(length);
    }
}
