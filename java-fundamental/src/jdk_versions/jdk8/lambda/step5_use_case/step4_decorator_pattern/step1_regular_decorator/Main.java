package jdk_versions.jdk8.lambda.step5_use_case.step4_decorator_pattern.step1_regular_decorator;

public class Main {

  /*
      Q. what is decorator pattern?

      기본 기을 가진 클래스에, 추가할 기능을 '장식' 삼아 추가해주는 패턴.

      기능 확장면에서 좋다.

      이 예제의 경우, '공격력'과 '방어력'를 item으로 '장식' 한다.

   */
  public static void main(String[] args) {

    //Q. 데코레이터 패턴 잘 적용 됬는데, 뭐가 문제?

    //A. 아이템의 공격/방어력이 적용될 때, Item enum에서 적용한 '합연산'밖에 안됨. 근데 내가 능동적으로 곱연산도 적용하고 싶다면?
    //   -> step2에 Function<Integer, Integer>을 사용하자.

    Availability avail = new Availability();

    System.out.println("Picked up a stick. Attack + 100");
    avail.setAttackItem(Item.STICK);
    System.out.println("Equipped a red gem. Attack increased by 5%");
    avail.setAttackItem(Item.RED_STONE);
    System.out.println("Received a rare sword. Attack +200");
    avail.setAttackItem(Item.SPECIAL_SWORD);

    System.out.println("Drank a blue potion. Defense + 100");
    avail.setDefenseItem(Item.BLUE_DRINK);
    System.out.println("Equipped a blue gem. Defense increased by 5%");
    avail.setDefenseItem(Item.BLUE_STONE);
    System.out.println("Picked up a golden armor. Defense +200");
    avail.setDefenseItem(Item.GOLD_ARMOR);

    System.out.println("Total ability: " + avail.totalAbility());
  }

}
