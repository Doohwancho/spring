package _2_oop._1_상태_데이터의_캡슐화.enum_.what.enum은_클래스다;

import java.util.Arrays;

/*
  Week과 Week1은 거의 같다. (enum이 기본 제공하는.values() 같은 메서드 달린거 뺴고..)
  enum
 */
public enum Week {
  SUNDAY("일"),
  MONDAY("월"),
  TUESDAY("화"),
  WEDNESDAY("수"),
  THURSDAY("목"),
  FRIDAY("금"),
  SATURDAY("토");

  private final String name ;

  Week(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public static Week findByName(String name) {
    return Arrays.stream(values())
        .filter(w -> w.getName().equals(name))
        .findAny()
        .orElseThrow(IllegalArgumentException::new);
  }
}
