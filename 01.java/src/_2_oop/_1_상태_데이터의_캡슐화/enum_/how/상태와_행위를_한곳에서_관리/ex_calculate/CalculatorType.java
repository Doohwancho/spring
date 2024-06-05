package _2_oop._1_상태_데이터의_캡슐화.enum_.how.상태와_행위를_한곳에서_관리.ex_calculate;

import java.util.function.Function;

public enum CalculatorType {

  CALC_A(value -> value), //calculate() 하면 expression.apply(value) 하면, value에 따라 다른 enum 실행함
  CALC_B(value -> value * 10),
  CALC_C(value -> value * 3),
  CALC_ETC(value -> 0L);

  private Function<Long, Long> expression;

  CalculatorType(Function<Long, Long> expression) {
    this.expression = expression;
  }

  public long calculate(long value){
    return expression.apply(value);
  }
}
