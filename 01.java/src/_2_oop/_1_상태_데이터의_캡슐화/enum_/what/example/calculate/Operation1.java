package _2_oop._1_상태_데이터의_캡슐화.enum_.what.example.calculate;

public enum Operation1 {
  PLUS, MINUS, DIVIDE, MULTIPLY;

  public double apply(double x, double y){
    switch(this){
      case PLUS: return x+y;
      case MINUS: return x-y;
      case DIVIDE: return x/y;
      case MULTIPLY: return x*y;
    }
    throw new AssertionError("알 수 없는 연산: "+ this);
  }
}
