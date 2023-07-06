package __3장.모든_객체의_공통_메서드.__10.hashCode_and_equals._1_what;

public class Main {
  public static void main(String[] args) {

    //Q. what is .hashCode()?
    //물리적으로 같은걸 비교하는게 아니라,
    //equals()처럼 논리적으로 같은지만 비교할 떄 쓰임.


    //Q. 언제 씀?
    //HashMap, HashSet 등에 객체를 key로 넣을 떄, hash값 필요하잖아? 이 때 씀.


    //case1) hashCode for Integer
    Integer i1 = new Integer(100);
    Integer i2 = new Integer(100);

    System.out.println(i1.hashCode()); //100 -> 왜냐면 Integer.hashCode()는 걍 return value임.
    System.out.println(i1.hashCode() == i2.hashCode()); // true. 왜? 논리적 값인 100이 같으니까.
    System.out.println(i1.equals(i2)); // true. 왜? 논리적인 값이 100으로 같으니까

    //case2) hashCode for String
    String s1 = new String("hello");
    String s2 = new String("hello");

    System.out.println(s1.hashCode()); //99162322 -> "hello"를 해쉬 함수 돌린게 저 값이 나오는 듯?
    System.out.println(s1.hashCode() == s2.hashCode()); // true -> instance가 달라고 논리값만 같으면 true 뜬다!
    System.out.println(s1.equals(s2)); // true. 내용이 같으니까.


    //case3) hashCode for Object
    Person person1 = new Person("Alice", 25);
    Person person2 = new Person("Alice", 25);

    System.out.println(person1.hashCode() == person2.hashCode()); //false
    System.out.println(person1.equals(person2)); //false

    //아니, 같은 new Integer(100) 비교해도 true 나왔고,
    //같은 new String("hello) 비교해도 true 나왔는데,
    //같은 객체를 비교했더니 hashCode()랑 equals()가 다르게 나오네?
    //Q. 왜 얘만 동등연산이 안됨?
    //A. 너가 뭔 객체를 만들지 어케알고 자바 언어가 동등연산 할 수 있는 .hashCode()랑 .equals()를 만듬?
    //   고로, 너가 custom 객체를 만들면, 동등 연산을 위한 .hashCode()랑 .equals()도 너가 @Override 받아서 재정의 해야 함

  }
}
