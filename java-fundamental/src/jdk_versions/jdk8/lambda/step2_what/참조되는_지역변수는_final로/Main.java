package jdk_versions.jdk8.lambda.step2_what.참조되는_지역변수는_final로;

import java.util.function.Function;

/*
  lambda 내부에서 참조할 수 있는 변수는 3종류
  1. local variable
  2. static variable
  3. member variable

  근데 local variable의 경우, final로 선언해주어야 하는데, (안그럼 lambda 연산 이후 해당 변수에 값 변경하면 에러남)
  이유는

  lambda 생성시 변수 참조하는 경우에, 'variable capture'라고, 그 변수의 상태를 복사해서 람다 안에서 보관하는데,
  만약 'variable capture' 이후, 원본 local variable의 값을 바꾸면, 상태 이상 에러가 나오니까,
  원본을 final로 해서 바꾸지 말라고 알려주는 것.

  나머지 static이나 멤버 변수의 경우, 모두가 공유하는 하나의 포인터를 넘겨받기 때문에 상태 이상 에러가 나올 걱정 안해도 됨.

 */

public class Main {

  public static void main(String[] args) {
    //step1) lambda 내부에서 그냥 int 변수 참조
    int baseValue = 1000; //final 붙여줘!

    Function<Integer, Integer> sum = input -> input + baseValue;

//    baseValue2 = 2000; //error! java: local variables referenced from a lambda expression must be final or effectively final

    Integer result = sum.apply(100);
    System.out.println(result);
  }

  //example)
  public void lambdaTest() {
    int base = 1000;
    new Thread(() -> {
      try {
        Thread.sleep(1000);
//        base = 999; //error! Variable used in lambda expression should be final or effectively final
      } catch(Exception e) {
        /*  do nothing */
      }
    }).start();
    System.out.println(base);
  }
}
