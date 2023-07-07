package jdk_versions.jdk8.lambda.step5_use_case.step2_execute_around_pattern.ex1_ensure_finally_for_Resource_cleanup;

public class Main {
  public static void main(String[] args) {
    Resource.use(resource -> {
      System.out.println("Doing something with the resource");
      // You can add more operations here
    });
  }
}
