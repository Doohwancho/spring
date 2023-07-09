package oop.pop.ex1.oop_도형;

import java.awt.Point;

public class Circle implements Shape {
  private Point center;
  private double radius;
  private final double PI = 3.141592653589793;

  public double area() {
    return PI * radius * radius;
  }
}
