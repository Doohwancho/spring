package _2_oop._1_상태_데이터의_캡슐화.access_modifier_.private_.ex2_util_class;

import java.util.ArrayList;
import java.util.List;

public class UtilityClass { //public으로 접근은 열어둠
  private UtilityClass() { //생성자가 private이라 객체 못만듬 + 상속도 안됨
    throw new AssertionError(); //없어도 되긴 하는데, secure coding하지 아무래도?
  }
  public static <T> List<T> asList(T... a) { //메서드는 static으로 해서 전역으로 접근해서 사용
    return new ArrayList<>(List.of(a));
  }

}
