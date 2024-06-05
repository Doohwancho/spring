package _2_oop._1_상태_데이터의_캡슐화.enum_.how.상태와_행위를_한곳에서_관리.ex_salesAmountType;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
public class EnumLambdaTest {
  @Test
  public void 매출타입별계산() {
    //given
    long txAmount = 10000L;

    //then
    long originAmount = SalesAmountType.ORIGIN_AMOUNT.calculate(txAmount);
    assertThat(originAmount, is(10000L));

    long supplyAmount = SalesAmountType.SUPPLY_AMOUNT.calculate(txAmount);
    assertThat(supplyAmount, is(9091L));

    long vatAmount = SalesAmountType.VAT_AMOUNT.calculate(txAmount);
    assertThat(vatAmount, is(909L));

    long notUsed = SalesAmountType.NOT_USED.calculate(txAmount);
    assertThat(notUsed, is(0L));
  }
}
