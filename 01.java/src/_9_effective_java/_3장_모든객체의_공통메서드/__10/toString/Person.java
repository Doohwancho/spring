package _9_effective_java._3장_모든객체의_공통메서드.__10.toString;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
      this.name = name;
      this.age = age;
    }

    @Override
    public String toString() {
      return "Person{name='" + name + "', age=" + age + "}";
    }
  }
