package _2_oop._1_상태_데이터의_캡슐화.enum_.how.데이터_그룹_관리.ex_paygroup;

public enum PayType {

  ACCOUNT_TRANSFER("계좌이체"),
  REMITTANCE("무통장입금"),
  ON_SITE_PAYMENT("현장결제"),
  TOSS("토스"),
  PAYCO("페이코"),
  CARD("신용카드"),
  KAKAO_PAY("카카오페이"),
  BAEMIN_PAY("배민페이"),
  POINT("포인트"),
  COUPON("쿠폰");

  private String title;

  PayType(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
}