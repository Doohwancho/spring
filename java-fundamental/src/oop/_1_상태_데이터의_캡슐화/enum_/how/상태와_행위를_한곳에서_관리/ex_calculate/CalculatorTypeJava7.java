package oop._1_상태_데이터의_캡슐화.enum_.how.상태와_행위를_한곳에서_관리.ex_calculate;

public enum CalculatorTypeJava7 {

  CALC_A {
    long calculate(long value){ return value;} //calculate() 이름은 같은데, enum 이름은 다른 여러 메서드 만들 수 있다.
  },
  CALC_B {
    long calculate(long value){ return value * 10;}
  },
  CALC_C {
    long calculate(long value){ return value * 3;}
  },
  CALC_ETC {
    long calculate(long value){ return 0L;}
  };

  abstract long calculate(long value); //하나의 abstract method, 명칭은 같은데,
}