package _1_syntax._1_by_jdk_versions.jdk8.default_method.diamond_problem;

public interface ParentB {
  default void hello(){
    System.out.println("check diamond problem");
  }
}
