package _2_oop._1_상태_데이터의_캡슐화.enum_.what.enum은_클래스다;

import java.util.Arrays;

public class Week1 {
  public static Week1 SUNDAY = new Week1("일");
  public static Week1 MONDAY = new Week1("월");
  public static Week1 TUESDAY = new Week1("화");
  public static Week1 WEDNESDAY = new Week1("수");
  public static Week1 THURSDAY = new Week1("목");
  public static Week1 FRIDAY = new Week1("금");
  public static Week1 SATURDAY = new Week1("토");

  private final String name ;

  Week1(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public static Week1 findByName(String name) {
    return Arrays.stream(values())
        .filter(w -> w.getName().equals(name))
        .findAny()
        .orElseThrow(IllegalArgumentException::new);
  }

  private static Week1[] values() {
    return new Week1[]{
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
    };
  }

}