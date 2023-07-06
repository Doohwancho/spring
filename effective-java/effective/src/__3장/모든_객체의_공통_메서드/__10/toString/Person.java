package __3장.모든_객체의_공통_메서드.__10.toString;

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
