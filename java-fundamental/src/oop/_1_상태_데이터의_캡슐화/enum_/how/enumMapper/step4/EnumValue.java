package oop._1_상태_데이터의_캡슐화.enum_.how.enumMapper.step4;

public class EnumValue {
  private String key;
  private String value;

  public EnumValue(EnumModel enumModel) {
    key = enumModel.getKey();
    value = enumModel.getValue();
  }

  public String getKey() {
    return key;
  }

  public String getValue() {
    return value;
  }
}
