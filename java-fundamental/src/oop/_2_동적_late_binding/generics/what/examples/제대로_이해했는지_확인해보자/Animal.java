package oop._2_동적_late_binding.generics.what.examples.제대로_이해했는지_확인해보자;

public class Animal implements Comparable<Animal> { //Q2를 표현하기 위해 implements Comparable<Animal> 함
  private String name;

  public Animal(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public int compareTo(Animal other) {
    return this.name.compareTo(other.getName());
  }
}
