package _2_oop._1_상태_데이터의_캡슐화.enum_.how.enum_필드로_같은의미끼리_묶기.ex2;

import java.time.LocalDate;

public enum LimitDayType {

  D_MINUS_1(-1, "D-1"), //D_MINUS_1 과 -1와 D-1은 같은 의미라 묶을 수 있음
  D_MINUS_2(-2, "D-2"),
  D_MINUS_3(-3, "D-3"),
  D_MINUS_4(-4, "D-4"),
  D_MINUS_5(-5, "D-5"),
  D_MINUS_6(-6, "D-6"),

  D_PLUS_1(1, "D+1"),
  D_PLUS_2(2, "D+2"),
  D_PLUS_3(3, "D+3"),
  D_PLUS_4(4, "D+4"),
  D_PLUS_5(5, "D+5"),
  D_PLUS_6(6, "D+6");

  private int day;
  private String viewTitle;

  LimitDayType(int day, String viewTitle) {
    this.day = day;
    this.viewTitle = viewTitle;
  }

  public LocalDate apply(LocalDate date){
    return date.plusDays(this.day);
  }
}
