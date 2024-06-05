package _9_effective_java._3장_모든객체의_공통메서드.__10.hashCode_and_equals._3_equals_재정의._1_why_override_equals;


public class Main {
  public static void main(String[] args) {

    //case5) override .equals() for Object
    Person person1 = new Person("Alice", 25);
    Person person2 = new Person("Alice", 25);

    System.out.println(person1.hashCode() == person2.hashCode()); //true
    System.out.println(person1.equals(person2)); //true


    //드디어 new Integer, new String의 논리적으로 값이 같은 애들만 .hashCode() && .equals()로 비교하면 true 뜨는게 아니라,
    //custom object도 true 뜬다!

  }
}
