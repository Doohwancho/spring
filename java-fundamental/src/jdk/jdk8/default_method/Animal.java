package jdk.jdk8.default_method;

public interface Animal {
  void eat();

  default void makeSound(){
    System.out.println("The animal makes a sound");
  }

}
