package _2_oop._1_상태_데이터의_캡슐화.enum_.how.데이터_그룹_관리.ex_paygroup;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;

public class PayGroupTest {
  @Test
  public void PayGroup에게_직접_결제종류_물어보기_문자열 () throws Exception {
    String payCode = selectPayCode();
    PayGroup payGroup = PayGroup.findByPayCode(payCode);

    assertThat(payGroup.name(), is("CARD"));
    assertThat(payGroup.getTitle(), is("카드"));
  }


  private String selectPayCode(){
    return "BAEMIN_PAY";
  }


  @Test
  public void PayGroup에게_직접_결제종류_물어보기_PayType () throws Exception {
    PayType payType = selectPayType();
    PayGroupAdvanced payGroupAdvanced = PayGroupAdvanced.findByPayType(payType);

    assertThat(payGroupAdvanced.name(), is("CARD"));
    assertThat(payGroupAdvanced.getTitle(), is("카드"));
  }



  private PayType selectPayType(){
    return PayType.BAEMIN_PAY;
  }


  @Test
  public void PayGroup으로_여러번_메소드_실행시켜야함_Legacy버전 () throws Exception {

        /*
            여러 비지니스 로직이 수행될 영역
         */
  }
}
