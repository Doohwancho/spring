package _2_oop._1_상태_데이터의_캡슐화.enum_.how.enumMapper.step1;

/*
  Q. 이 클래스에서 뭐가 문제?

  String commissionType; // 수수료 타입 (예: 퍼센테이지, 금액)
  String commissionCutting; // 수수료 절삭 (예: 반올림, 올림, 버림)


  저걸 String으로 퉁 쳐버렸는데...

  1. commissionType과 commissionCutting은 IDE 지원을 받을 수 없다.
     ex. 자동완성, 오타검증 등등
  2. commissionType과 commissionCutting의 변경 범위가 너무 크다.
     ex. 예를 들어, commissionType의 money를 mount로 변경해야 한다면 프로젝트 전체에서 money를 찾아 변경해야 합니다.
         추가로 commissionType의 money 인지, 다른 domain의 money인지 확인하는 과정도 추가되어 비용이 배로 들어가게 됩니다.
  3. commissionType과 commissionCutting에 잘못된 값이 할당되도 검증하기가 어렵다.
     ex. percent, money가 아닌 값이 할당되는 경우를 방지하기 위해 검증 메소드가 필요합니다.
  4. commissionType과 commissionCutting의 허용된 값 범위를 파악하기 힘들다.
     ex. 예를 들어, commissionType과 commissionCutting을 select box로 표기해야 한다고 생각해보겠습니다.
         이들의 가능한 값 리스트가 필요한데, 현재 형태로는 하드코딩 할 수 밖에 없습니다.




 */


//@Entity
public class Contract {

//  @Id
//  @GeneratedValue
  private Long id;

//  @Column(nullable = false)
  private String company;

//  @Column(nullable = false)
  private double commission; // 수수료

//  @Column(nullable = false)
  private String commissionType; // 수수료 타입 (예: 퍼센테이지, 금액)

//  @Column(nullable = false)
  private String commissionCutting; // 수수료 절삭 (예: 반올림, 올림, 버림)

  public Contract() {}

  public Contract(String company, double commission, String commissionType, String commissionCutting) {
    this.company = company;
    this.commission = commission;
    this.commissionType = commissionType;
    this.commissionCutting = commissionCutting;
  }

  public Long getId() {
    return id;
  }

  public String getCompany() {
    return company;
  }

  public double getCommission() {
    return commission;
  }

  public String getCommissionType() {
    return commissionType;
  }

  public String getCommissionCutting() {
    return commissionCutting;
  }

}