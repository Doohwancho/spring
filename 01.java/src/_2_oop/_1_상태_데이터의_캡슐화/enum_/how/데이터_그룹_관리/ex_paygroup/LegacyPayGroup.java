package _2_oop._1_상태_데이터의_캡슐화.enum_.how.데이터_그룹_관리.ex_paygroup;

public class LegacyPayGroup {

  public static String getPayGroup(final String payCode){

    if("ACCOUNT_TRANSFER".equals(payCode) || "REMITTANCE".equals(payCode) || "ON_SITE_PAYMENT".equals(payCode) || "TOSS".equals(payCode)){
      return "CASH";

    } else if("PAYCO".equals(payCode) || "CARD".equals(payCode) || "KAKAO_PAY".equals(payCode) || "BAEMIN_PAY".equals(payCode)){
      return "CARD";

    } else if("POINT".equals(payCode) || "COUPON".equals(payCode)){
      return "ETC";

    } else {
      return "EMPTY";
    }
  }

  public void pushByPayGroup(final String payGroupCode){

    if("CASH".equals(payGroupCode)){

      pushCashMethod();

    } else if("CARD".equals(payGroupCode)){

      pushCardMethod();

    } else if("ETC".equals(payGroupCode)){

      pushEtcMethod();

    } else {
      throw new RuntimeException("payGroupCode가 없습니다");
    }
  }

  public void printByPayGroup(final String payGroupCode){

    if("CASH".equals(payGroupCode)){

      doCashMethod();

    } else if("CARD".equals(payGroupCode)){

      doCardMethod();

    } else if("ETC".equals(payGroupCode)){

      doEtcMethod();

    } else {
      throw new RuntimeException("payGroupCode가 없습니다");
    }
  }

  private void pushCashMethod() {
    System.out.println("PUSH CASH");
  }

  private void pushCardMethod() {
    System.out.println("PUSH CARD");
  }

  private void pushEtcMethod() {
    System.out.println("PUSH ETC");
  }

  private void doCashMethod() {
    System.out.println("CASH!");
  }

  private void doCardMethod() {
    System.out.println("CARD!");
  }

  private void doEtcMethod() {
    System.out.println("ETC!");
  }
}