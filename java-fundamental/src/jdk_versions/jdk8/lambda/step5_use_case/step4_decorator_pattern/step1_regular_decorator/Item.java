package jdk_versions.jdk8.lambda.step5_use_case.step4_decorator_pattern.step1_regular_decorator;

public enum Item {
  STICK(100) {
    @Override
    public long apply(long value) {
      return value + getValue();
    }
  },
  RED_STONE(5) {
    @Override
    public long apply(long value) {
      return value + value * getValue() / 100; //step2와의 차이점: step1은 합연산이 강제되는데, step2에선 Function으로 합연산 할건지 곱연산 할건지 명시가능
    }
  },
  SPECIAL_SWORD(200) {
    @Override
    public long apply(long value) {
      return value + getValue();
    }
  },
  BLUE_DRINK(100) {
    @Override
    public long apply(long value) {
      return value + getValue();
    }
  },
  BLUE_STONE(5) {
    @Override
    public long apply(long value) {
      return value + value * getValue() / 100;
    }
  },
  GOLD_ARMOR(200) {
    @Override
    public long apply(long value) {
      return value + getValue();
    }
  };

  private final long value;

  Item(long value) {
    this.value = value;
  }

  public long getValue() {
    return value;
  }

  public abstract long apply(long value);
}
