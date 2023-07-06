package jdk.jdk8.default_method.diamond_problem;

public interface ParentB {
  default void hello(){
    System.out.println("check diamond problem");
  }
}
