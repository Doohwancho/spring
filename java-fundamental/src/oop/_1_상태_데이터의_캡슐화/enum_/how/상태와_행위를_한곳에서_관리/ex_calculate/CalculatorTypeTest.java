package oop._1_상태_데이터의_캡슐화.enum_.how.상태와_행위를_한곳에서_관리.ex_calculate;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class CalculatorTypeTest {

  private String selectCode(){
    return "CALC_B";
  }

  private CalculatorType selectType() {
    return CalculatorType.CALC_B;
  }

  @Test
  public void 코드에_따라_서로다른_계산하기_기존방식 () throws Exception {
    String code = selectCode();
    long originValue = 10000L;
    long result = LegacyCalculator.calculate(code, originValue);

    assertThat(result, is(100000L));
  }


  @Test
  public void 코드에_따라_서로다른_계산하기_Enum () throws Exception {
    CalculatorType code = selectType();
    long originValue = 10000L;
    long result = code.calculate(originValue);

    assertThat(result, is(100000L));
  }


  @Test
  public void Java7이하_버전에서_사용하기 () throws Exception {
    CalculatorTypeJava7 enum7 = CalculatorTypeJava7.CALC_A;

    assertThat(enum7.calculate(10), is(10L));
  }

}
