package oop._1_상태_데이터의_캡슐화.enum_.how.상태와_행위를_한곳에서_관리.ex_calculate;

public class LegacyCalculator {
  public static long calculate(String code, long originValue){

    if("CALC_A".equals(code)){
      return originValue;
    } else if("CALC_B".equals(code)){
      return originValue * 10;
    } else if("CALC_C".equals(code)){
      return originValue * 3;
    } else {
      return 0;
    }
  }
}
