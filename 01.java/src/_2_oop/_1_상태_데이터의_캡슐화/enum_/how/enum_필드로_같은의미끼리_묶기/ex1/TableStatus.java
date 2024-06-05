package _2_oop._1_상태_데이터의_캡슐화.enum_.how.enum_필드로_같은의미끼리_묶기.ex1;

public enum TableStatus {
  Y("1", true), //"Y", "1", true는 모두 같은 의미 -> enum으로 묶을 수 있다.
  N("0", false);

  private String table1Value;
  private boolean table2Value;

  TableStatus(String table1Value, boolean table2Value) {
    this.table1Value = table1Value;
    this.table2Value = table2Value;
  }

  public String getTable1Value() {
    return table1Value;
  }

  public boolean isTable2Value() {
    return table2Value;
  }
}
