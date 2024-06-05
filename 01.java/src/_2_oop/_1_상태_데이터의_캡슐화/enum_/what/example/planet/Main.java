package _2_oop._1_상태_데이터의_캡슐화.enum_.what.example.planet;

public class Main {

  public static void main(String[] args) {
    double earthWeight = Double.parseDouble(args[0]);
    double mass = earthWeight / Planet.EARTH.surfaceGravity();
    for(Planet p : Planet.values()){
      System.out.printf("%에서의 무게는 %f이다.\n", p, p.surfaceGravity(mass));
    }
  }

}
