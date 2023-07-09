package oop.pop.ex1.pop_도형;

/*
  pop의 장점
  장점은 컴퓨터의 처리구조와 유사해 실행 속도가 빠름

  pop의 단점
  1. 단점은 각 코드가 매우 유기성이 높기 때문에 수정이 어렵다.
  2. 또한, 유지보수가 어려우며, 실행 순서가 정해져 있으므로 코드의 순서가 바뀌면 동일한 결과를 보장하기 어려우며 디버깅 또한 어렵다.
 */

public class Geometry {
  public final double PI = 3.141592653589793;

  public double area(Object shape) throws NoSuchShapeException {
    if (shape instanceof Square) {
      Square s = (Square) shape;
      return s.side * s.side;
    } else if (shape instanceof Rectangle) {
      Rectangle r = (Rectangle) shape;
      return r.height * r.width;
    } else if (shape instanceof Circle) {
      Circle c = (Circle) shape;
      return PI * c.radius * c.radius;
    }

    throw new NoSuchShapeException();
  }

  private class NoSuchShapeException extends Exception {
    NoSuchShapeException(){
      super();
    }
  }
}
