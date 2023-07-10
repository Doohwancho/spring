package oop._2_동적_late_binding.generics.examples.제대로_이해했는지_확인해보자;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

  public static <T extends Comparable<? super T>> void sort(List<T> list) {
    Collections.sort(list);
  }
  public static void main(String[] args) {
    //Q1. Collection.sort(List, Comparator)에서
    //   static <T> void sort(List<T> list, Comparator<? super T> c)   <- 무슨 말?

    List<Integer> list = new ArrayList<>();
    list.add(100);
    list.add(50);
    list.add(10);

    Collections.sort(list, (a, b) -> a.intValue() - b.intValue()); //sort ASC
    list.forEach(x -> System.out.println(x));

    //1. <T> void sort(List<T> list, ...) 에서 <T>는, generic method에서 local로 선언한 T라, 클래스 옆에 붙은 clas List<T>와는 다른 T다.
    //2. 해당 list가 List<Integer>로 선언되었다면, 저 T는 Integer가 되고, List<String>으로 선언되었다면, 저 T는 String이 된다.
    //3. 근데 이 T가 Comparator<? super T> c에 쓰이는데, ? super T에 ?는 wildcard로써, T와 조상 타입만 받는다는 것. 예를들어, T가 Integer이고, T의 조상이 Number이면, Comparator 만들 때 타입은 이 둘만 받겠다는 것.

    System.out.println("================================");

    //Q2. 그럼 public static <T extends Comparable<? super T>> void sort(List<T> list)  <--- 이건 무슨 뜻?

    List<Dog> dogs = new ArrayList<>();
    dogs.add(new Dog("Rex"));
    dogs.add(new Dog("Fido"));
    dogs.add(new Dog("Buddy"));

    sort(dogs); //sort ASC

    for (Dog dog : dogs) {
      System.out.println(dog.getName());
    }

    //1. generic method로 선언된 로컬 <T>는 List<Integer> 라면 Integer, List<String>이라면 String이 된다. 이 경우 List<Dog> 니까, T는 Dog가 된다.
    //2. 로컬로 선언된 T의 범위는, <T extends Comparable> 이기 때문에, Comparable을 implements 받은 클래스 들(ex. Boolean, Byte, Character, String, Integer, etc)이나, 인위적으로 implements Comparable한 일반 클래스나 그 자손만 가능하다.(extends 니까).
    // -> 이 경우, T는 Dog인데, T extends Comparable에서, Dog(T)의 부모인 class Animal이 implements Comparable 했으니까, 이게 성립할 수 있는 것.
    //3. 근데 Comparable<? super T>는 T와 그 조상 타입만 가능(lower bound) -> Dog의 상튀 타입인 class Animal implements Comparator<Animal>로 함. 걍 class Dog implements Comparator<Dog> 했어도 됬었음. 걍 ? super T에서 super니까, T의 조상이 implements Comparator 했어도 쳐준다는 것.
    //  -> T = Dog, Comparator<? super T>에서 ? 는 Animal이 됨.

    //정리하자면,
    //public static <T extends Comparable<? super T>> void sort(List<T> list) 에서
    //T -> Dog, ? -> Animal
    //Dog extends Comparable<Animal> 인 것.
    //왜? class Animal implements Comparable<Animal> 했으니까.
  }

}
