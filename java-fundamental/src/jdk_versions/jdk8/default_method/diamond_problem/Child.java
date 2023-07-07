package jdk_versions.jdk8.default_method.diamond_problem;

public class Child { //implements ParentA, ParentB{ //error! diamond problem!

  public static void main(String[] args) {
//    hello(); //error! diamond problem

    // 내 생각
    // default method(in interface)가 나왔다길래, 그럼 상속에 다이아몬드 문제는 어케 되는겨? 해서 실험해봤더니
    // 다행히 컴파일 타임에서 에러를 잡아준다.
  }

  /**
   * error log
   *
   * /Users/cho-cho/dev/tree/spring/java-fundamental/src/jdk/jdk8/default_method/diamond_problem/Child.java:3:8
   * java: types jdk.jdk8.default_method.diamond_problem.ParentA and jdk.jdk8.default_method.diamond_problem.ParentB are incompatible;
   *   class jdk.jdk8.default_method.diamond_problem.Child inherits unrelated defaults for hello() from types jdk.jdk8.default_method.diamond_problem.ParentA and jdk.jdk8.default_method.diamond_problem.ParentB
   */
}
