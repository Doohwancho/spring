package oop._1_상태_데이터의_캡슐화.enum_.how.enumMapper.step3;

//이러면 1번 문제 해결 뿐만 아니라,
//2번 문제도 해결 -> enum에서 나올 수 있는 경우의 수를 제한시켰으니까. ex. EnumContract.CommissionType.MONEY
//3번 문제도 해결 -> 잘못된 값 넣으면 compile time, ide 선에서 에러 던짐
//4번 문제도 해결 -> 허용하는 값의 범위 제한 가능
class EnumContract {
//  @Column(nullable = false)
//  @Enumerated(EnumType.STRING) // enum의 name을 DB에 저장하기 위해, 없을 경우 enum의 숫자가 들어간다.
  private CommissionType commissionType; // 수수료 타입 (예: 퍼센테이지, 금액)

//  @Column(nullable = false)
//  @Enumerated(EnumType.STRING)
  private CommissionCutting commissionCutting; // 수수료 절삭 (예: 반올림, 올림, 버림)

  public enum CommissionType {

    PERCENT("percent"),
    MONEY("money");

    private String value;

    CommissionType(String value) {
      this.value = value;
    }

    public String getKey() {
      return name();
    }

    public String getValue() {
      return value;
    }
  }

  public enum CommissionCutting {
    ROUND("round"),
    CEIL("ceil"),
    FLOOR("floor");

    private String value;

    CommissionCutting(String value) {
      this.value = value;
    }

    public String getKey() {
      return name();
    }

    public String getValue() {
      return value;
    }
  }

}
