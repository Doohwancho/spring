package _2_oop.pop.ex1.oop_도형;

import java.awt.Point;

public class Rectangle implements Shape {
  private Point toLeft;
  private double height;
  private double width;

  public double area() {
    return height * width;
  }
}
