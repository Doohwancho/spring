package _2_oop._1_상태_데이터의_캡슐화.enum_.how.상태와_행위를_한곳에서_관리.ex_calculate;

public class CodeTable {
  private long value;
  private String code;

//  @Column
//  @Enumerated(EnumType.STRING) //만약 Entity 클래스에 선언하실 경우에는 String이 아닌 enum을 선언
  private CalculatorType calculatorType;


  public CodeTable() {}

  public CodeTable(String code) {
    this.code = code;
  }

  public CodeTable(CalculatorType calculatorType) {
    this.calculatorType = calculatorType;
  }

  public String getCode() {
    return code;
  }

  public CalculatorType getCalculatorType() {
    return calculatorType;
  }
}
