package _1_syntax._1_by_jdk_versions.jdk8.lambda.step3_functional_interface.unaryOperator;


public class Main {

  public static void main(String[] args) {
    CustomUnaryOperator<Integer> square = x -> (x * x);
    System.out.println(square.apply(5));
  }

}
