package jdk.jdk8.default_method;

public class Main {
  public static void main(String[] args) {
    Dog dog = new Dog();
    dog.eat();
    dog.makeSound(); // 이 메서드를 Dog에서 직접 구현 안했는데 마치 상속처럼 자식 implements에게 모두 구현되어있는걸 확인할 수 있다. 
  }
}
