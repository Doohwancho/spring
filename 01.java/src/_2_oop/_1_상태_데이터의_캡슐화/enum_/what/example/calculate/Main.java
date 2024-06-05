package _2_oop._1_상태_데이터의_캡슐화.enum_.what.example.calculate;

public class Main {

  public static void main(String[] args) {
    double x = 1.3;
    double y = 3.2;

    for(Operation2 op : Operation2.values()){
      System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
    }

    Operation1.PLUS.apply(x,y);
  }

}
