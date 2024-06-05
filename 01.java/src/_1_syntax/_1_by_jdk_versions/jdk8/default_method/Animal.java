package _1_syntax._1_by_jdk_versions.jdk8.default_method;

public interface Animal {
  void eat();

  default void makeSound(){
    System.out.println("The animal makes a sound");
  }

}
