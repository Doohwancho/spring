package _2_oop._1_상태_데이터의_캡슐화.enum_.how.enumMapper.step2;

//이러면 step1에서 1번쨰 문제: ide에서 자동완성 지원은 받는데, 나머지 2,3,4 번 문제는 해결이 안됨..
public interface Commission {
  String TYPE_PERCENT = "percent";
  String TYPE_MONEY = "money";

  String CUTTING_ROUND = "round";
  String CUTTING_CEIL = "ceil";
  String CUTTING_FLOOR = "floor";
}
