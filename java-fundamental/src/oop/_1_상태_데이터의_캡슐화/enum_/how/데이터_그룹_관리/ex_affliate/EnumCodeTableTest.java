package oop._1_상태_데이터의_캡슐화.enum_.how.데이터_그룹_관리.ex_affliate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
public class EnumCodeTableTest {
  @Test
  public void 코드테이블_대신_enum() {
    //given
    Affiliate.Code sisters = Affiliate.Code.WOOWA_SISTERS;
    Affiliate.Code children = Affiliate.Code.WOOWA_CHILDREN;
    Affiliate.Code adults = Affiliate.Code.WOOWA_ADULTS;
    Affiliate.Code adultsValueOf = Affiliate.Code.valueOf("WOOWA_ADULTS");

    //then
    assertThat(sisters.getViewName(), is("우아한자매들"));
    assertThat(children.getBizTypeCode(), is("s0099"));
    assertThat(adults.getCompanyCode(), is("w03"));
    assertThat(adultsValueOf.getViewName(), is("우아한어른들"));
  }
}
