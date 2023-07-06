package __3장.모든_객체의_공통_메서드.__10.hashCode_and_equals._3_equals_재정의._1_why_override_equals;

public class Person {
  private String name;
  private int age;

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }
  @Override
  public int hashCode() {
    int result = 17; //소수
    //주의! equals()로 비교되는 필드들을 꼭 아래 연산에 포함시켜야 한다!!
    result = 31 * result + name.hashCode(); //31이라는 소수 값을를 매 step마다 곱해줘서 숫자의 order 문제(?)를 해결했다고 한다.
    result = 31 * result + age;
    return result;
  }
  //수동 정의 안하고 guava library 중에 object hashing해주는 애 쓰면 성능 좀 더 좋다고 한다.


  //step5) override equals()
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Person person = (Person) obj;
    return age == person.age && name.equals(person.name);
  }
}

