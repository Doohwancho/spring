package _2_oop.pop.ex1.oop_도형;

import java.awt.Point;

public class Square implements Shape {
  private Point toLeft;
  private double side;

  public double area() {
    return side * side;
  }
}