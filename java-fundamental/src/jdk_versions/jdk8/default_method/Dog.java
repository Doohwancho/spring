package jdk_versions.jdk8.default_method;

public class Dog implements Animal{
  @Override
  public void eat() {
    System.out.println("The dog eats");
  }

  // No need to implement makeSound(), it's optional due to being a default method
}
