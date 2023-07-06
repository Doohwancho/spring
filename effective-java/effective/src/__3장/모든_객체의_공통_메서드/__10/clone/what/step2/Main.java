package __3장.모든_객체의_공통_메서드.__10.clone.what.step2;

public class Main {

  public static void main(String[] args)
  {
    Student s1 = new Student("Ashish", 121);

    //step2)
    Student s2 = s1.clone();

    System.out.println(s1.toString()); //Student [name=Ashish, id=121]
    System.out.println(s2.toString()); //Student [name=Ashish, id=121]  -> s1, s2 객체의 논리값은 같으나,

    System.out.println(s1.getClass() == s2.getClass()); //true - 클래스는 같은데,

    System.out.println(s1 == s2); //false - 두 instance는 다른 객체다. 왜? deep copy니까.

    System.out.println(System.identityHashCode(s1)); //713338599
    System.out.println(System.identityHashCode(s2)); //168423058  -> 물리적 메모리 주솟값은 다르다. (완전 메모리 주솟값이 아닌 jvm상에 부여된 논리적 주솟값이다.)

    //결론: clone()은 shallow copy가 아니라, deepcopy다.
  }
}
