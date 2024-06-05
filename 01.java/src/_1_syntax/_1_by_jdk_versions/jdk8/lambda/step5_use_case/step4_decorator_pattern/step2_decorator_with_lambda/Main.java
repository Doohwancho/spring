package _1_syntax._1_by_jdk_versions.jdk8.lambda.step5_use_case.step4_decorator_pattern.step2_decorator_with_lambda;

public class Main {
  public static void main(String[] args) {
    Availability avail = new Availability();

    System.out.println("막대기를 주웠습니다. 공격력 + 100");
    avail.setAttactItem(attactPoint ->
        attactPoint + (long)Items.STICK.getValue());    //얘는 합연산으로 적용!
    System.out.println("빨간 보석을 착용합니다. 공격력 5% 증가");
    avail.setAttactItem(attactPoint ->
        Math.round(attactPoint * Items.RED_STONE.getValue())); //얘는 곱연산으로 적용!
    System.out.println("희귀한 검을 받았습니다. 공격력 +200 증가");
    avail.setAttactItem(attactPoint ->
        attactPoint + (long)Items.SPECIAL_SWORD.getValue());

    System.out.println("파란 물약을 먹습니다. 방어력 + 100");
    avail.setDefenceItem(defencePoint ->
        defencePoint + (long)Items.BLUE_DRINK.getValue());
    System.out.println("파란 보석을 착용합니다. 방어력 5% 증가");
    avail.setDefenceItem(defencePoint ->
        Math.round(defencePoint * Items.BLUE_STONE.getValue()));
    System.out.println("황금갑옷을 주웠습니다. 방어력 +200 증가");
    avail.setDefenceItem(defencePoint ->
        defencePoint + (long)Items.GOLD_ARMOR.getValue());

    System.out.println("전체 능력: " + avail.totalAvaility());

  }
}
