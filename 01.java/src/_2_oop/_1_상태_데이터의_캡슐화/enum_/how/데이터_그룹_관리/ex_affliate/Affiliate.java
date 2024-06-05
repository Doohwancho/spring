package _2_oop._1_상태_데이터의_캡슐화.enum_.how.데이터_그룹_관리.ex_affliate;

//jpa에서 enum 관리할 때, nested enum으로 관리하면 되겠다~ 어디서 enum 참조할거 아니면

//@Entity
//@Getter
public class Affiliate {

//  @Id
//  @GeneratedValue
  private Long id;

//  @Column
  private String name;

//  @Column
  private String representative;

//  @Column
  private String address;

//  @Column
//  @Enumerated(EnumType.STRING)
  private Code code;

  public enum Code {
    WOOWA_SISTERS("우아한자매들", "w01", "s0001"),
    WOOWA_CHILDREN("우아한아이들", "w02", "s0099"),
    WOOWA_ADULTS("우아한어른들", "w03", "s1201");

    private String viewName;
    private String companyCode;
    private String bizTypeCode;

    Code(String viewName, String companyCode, String bizTypeCode) {
      this.viewName = viewName;
      this.companyCode = companyCode;
      this.bizTypeCode = bizTypeCode;
    }

    public String getViewName() {
      return viewName;
    }

    public String getCompanyCode() {
      return companyCode;
    }

    public String getBizTypeCode() {
      return bizTypeCode;
    }
  }
}
