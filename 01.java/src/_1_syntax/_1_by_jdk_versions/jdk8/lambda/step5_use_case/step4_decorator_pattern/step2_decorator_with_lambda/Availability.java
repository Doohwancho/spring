package _1_syntax._1_by_jdk_versions.jdk8.lambda.step5_use_case.step4_decorator_pattern.step2_decorator_with_lambda;

import java.util.ArrayList;
import java.util.function.Function;

public class Availability {
  private long mAttact = 100; //기존 공격력이 있고
  private long mDefence = 100; //기존 방어력이 있는데,

  private ArrayList<Function<Long, Long>> mAttactDecorators; //아이템 추가로 먹으면 기존 공격력에 '장식', decorate 해줌
  private ArrayList<Function<Long, Long>> mDefenceDecorators; //아이템 추가로 먹으면 기존 방어력에 '장식', decorate 해줌

  public Availability() {
    mAttactDecorators = new ArrayList<>();
    mDefenceDecorators = new ArrayList<>();
  }

  public long totalAvaility() {
    Function<Long, Long> attactFunc = mAttactDecorators.stream()
        .reduce((item0, item1) -> item0.andThen(item1))
        .orElseGet(Function::identity);

    Function<Long, Long> defenceFunc = mDefenceDecorators.stream()
        .reduce((item0, item1) -> item0.andThen(item1))
        .orElseGet(Function::identity);

    mAttact = attactFunc.apply(mAttact);
    mDefence = defenceFunc.apply(mDefence);

    return mAttact + mDefence;
  }

  public void setAttactItem(final Function<Long, Long> operator) { //Availability 클래스에선, 합연산인지 곱연산인지 몰라도 된다. 그건 Main에서 신경쓴다.
    mAttactDecorators.add(operator);
  }

  public void setDefenceItem(final Function<Long, Long> operator) {
    mDefenceDecorators.add(operator);
  }

}
