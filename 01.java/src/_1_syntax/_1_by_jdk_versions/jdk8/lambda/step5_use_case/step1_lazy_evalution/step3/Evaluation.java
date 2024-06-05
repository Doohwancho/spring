package _1_syntax._1_by_jdk_versions.jdk8.lambda.step5_use_case.step1_lazy_evalution.step3;

import java.util.function.Supplier;

public class Evaluation {
  public static boolean evaluate(final int value) {
    System.out.println("evaluating ..." + value);
    simulateTimeConsumingOp(2000);
    return value > 100;
  }

  public static void simulateTimeConsumingOp(final int millseconds) {
    try {
      Thread.sleep(2000);
    } catch(Exception ex) { throw new RuntimeException(ex); }
  }

  //case1) eager
  public static void eagerEvaluator(final boolean input1, final boolean input2) {
    System.out.println("eagerEvaluator called...");
    System.out.println("accept?: " + (input1 && input2));
  }

  //case2) lazy
  public static void lazyEvaluator(final Supplier<Boolean> input1, final Supplier<Boolean> input2) {
    System.out.println("lazyEvaluator called...");
    System.out.println("accept?: " + (input1.get() && input2.get()));
  }
  public static void main(String[] args) {
    //case1) eager
//    System.out.println("//" + "START:EAGER_OUTPUT");
//    eagerEvaluator(evaluate(1), evaluate(2));
//    System.out.println("//" + "END:EAGER_OUTPUT");

    //console.log
    /*
    //START:EAGER_OUTPUT
    evaluating ...1
    evaluating ...2
    eagerEvaluator called...
    accept?: false
    //END:EAGER_OUTPUT
     */


    System.out.println("================================");

    //case2) lazy
    System.out.println("//" + "START:LAZY_OUTPUT");
    lazyEvaluator(() -> evaluate(1), () -> evaluate(2));
    System.out.println("//" + "END:LAZY_OUTPUT");

    /*
      console.log

      //START:LAZY_OUTPUT
      lazyEvaluator called...
      evaluating ...1                    -> 수행시간이 줄었다!
      accept?: false
      //END:LAZY_OUTPUT

     */


  }
}
