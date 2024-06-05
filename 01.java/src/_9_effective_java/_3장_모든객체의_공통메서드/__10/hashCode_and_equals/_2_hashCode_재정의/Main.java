package _9_effective_java._3장_모든객체의_공통메서드.__10.hashCode_and_equals._2_hashCode_재정의;

public class Main {
  public static void main(String[] args) {

    //case4) override .hashCode() for Object
    Person person1 = new Person("Alice", 25);
    Person person2 = new Person("Alice", 25);

    System.out.println(person1.hashCode() == person2.hashCode()); //true -> 바뀜! .hashCode()를 재정의해줘서.
    System.out.println(person1.equals(person2)); //false

  }
}
