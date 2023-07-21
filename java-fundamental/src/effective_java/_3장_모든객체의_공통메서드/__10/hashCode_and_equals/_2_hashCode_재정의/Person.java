package effective_java._3장_모든객체의_공통메서드.__10.hashCode_and_equals._2_hashCode_재정의;

public class Person {
  private String name;
  private int age;

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  //case4) hashCode() 재정의
  @Override
  public int hashCode() {
    int result = 17; //소수
    //주의! equals()로 비교되는 필드들을 꼭 아래 연산에 포함시켜야 한다!!
    result = 31 * result + name.hashCode(); //31이라는 소수 값을를 매 step마다 곱해줘서 숫자의 order 문제(?)를 해결했다고 한다.
    result = 31 * result + age;
    return result;
  }
  //수동 정의 안하고 guava library 중에 object hashing해주는 애 쓰면 성능 좀 더 좋다고 한다.
}

