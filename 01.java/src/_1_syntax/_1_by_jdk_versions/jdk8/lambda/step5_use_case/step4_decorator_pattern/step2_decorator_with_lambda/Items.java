package _1_syntax._1_by_jdk_versions.jdk8.lambda.step5_use_case.step4_decorator_pattern.step2_decorator_with_lambda;

public enum Items {
  STICK(100), //step1과는 다르게 깔끔해짐. 합연산 할건지, 곱연산 할건지는 Main에서 넣어줌.
  RED_STONE(0.05),
  SPECIAL_SWORD(200),
  BLUE_DRINK(100),
  BLUE_STONE(0.05),
  GOLD_ARMOR(200);

  private final double value;

  Items(double value) {
    this.value = value;
  }

  public double getValue() {
    return value;
  }
}

