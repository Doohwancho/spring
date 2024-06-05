package _1_syntax._1_by_jdk_versions.jdk8.lambda.step5_use_case.step4_decorator_pattern.step1_regular_decorator;

import java.util.ArrayList;
import java.util.List;

public class Availability {
  private long mAttack = 100; //기존 공격력이 있고
  private long mDefense = 100; //기존 방어력이 있는데,

  private List<Item> mAttackItems; //아이템 추가로 먹으면 기존 공격력에 '장식', decorate 해줌
  private List<Item> mDefenseItems; //아이템 추가로 먹으면 기존 방어력에 '장식', decorate 해줌

  public Availability() {
    mAttackItems = new ArrayList<>();
    mDefenseItems = new ArrayList<>();
  }

  public long totalAbility() {
    for (Item item : mAttackItems) {
      mAttack = item.apply(mAttack);
    }
    for (Item item : mDefenseItems) {
      mDefense = item.apply(mDefense);
    }
    return mAttack + mDefense;
  }

  public void setAttackItem(Item item) {
    mAttackItems.add(item);
  }

  public void setDefenseItem(Item item) {
    mDefenseItems.add(item);
  }
}

