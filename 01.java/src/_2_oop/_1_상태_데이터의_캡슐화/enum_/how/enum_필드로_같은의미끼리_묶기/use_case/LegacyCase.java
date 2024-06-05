package _2_oop._1_상태_데이터의_캡슐화.enum_.how.enum_필드로_같은의미끼리_묶기.use_case;

public class LegacyCase {
  public String toTable1Value(String originValue) {
    if("Y".equals(originValue)){
      return "1";
    } else {
      return "0";
    }
  }

  public boolean toTable2Value(String originValue){
    if("Y".equals(originValue)){
      return true;
    } else {
      return false;
    }
  }
}
