package __3장.모든_객체의_공통_메서드.__10.clone.why_override_clone.step3;

public class Main {

  public static void main(String[] args) {
    CustomStack s1 = new CustomStack(10);

    s1.push(new 가변_커스텀_객체(1, "hello", new 가변_커스텀_객체의_커스텀_객체(100, "a")));
    s1.push(new 가변_커스텀_객체(2, "world", new 가변_커스텀_객체의_커스텀_객체(200, "b")));

    CustomStack s2 = s1.clone();

    System.out.println(s1 == s2); //false
    System.out.println(s1.equals(s2)); //false
    System.out.println(s2.getSize()); //2

    System.out.println(s1.toString()); //[(value=1, name=hello, object=value=100, name=a), (value=2, name=world, object=value=200, name=b)]
    System.out.println(s2.toString()); //[(value=1, name=hello, object=value=100, name=a), (value=2, name=world, object=value=200, name=b)]

    s2.push(new 가변_커스텀_객체(3, "people", new 가변_커스텀_객체의_커스텀_객체(300, "c")));

    //case2) 가변 객체 따로 .clone() 처리 해주면?
    System.out.println(s1.toString()); //[value=1, name=hello, value=2, name=world]
    System.out.println(s2.toString()); //[value=1, name=hello, value=2, name=world, value=3, name=people] -> deep copy라 원본 s1에 영향을 안줌

  }
}
