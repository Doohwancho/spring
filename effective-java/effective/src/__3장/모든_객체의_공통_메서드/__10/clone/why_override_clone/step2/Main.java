package __3장.모든_객체의_공통_메서드.__10.clone.why_override_clone.step2;

public class Main {

  public static void main(String[] args) {
    Stack s1 = new Stack(2);

    s1.push("one");
    s1.push("two");

    Stack s2 = s1.clone();

    System.out.println(s1 == s2); //false
    System.out.println(s1.equals(s2)); //false
    System.out.println(s2.getSize()); //2

    System.out.println(s1.toString()); //[one, two]
    System.out.println(s2.toString()); //[one, two]

    s2.pop();
    s2.push("three");

    //case1) 가변 객체 따로 .clone() 처리 안해주면?
    System.out.println(s1.toString()); //[one, three] -> shallow copy라 s2 바꾸면 s1도 바뀜.
    System.out.println(s2.toString()); //[one, three]

    //case2) 가변 객체 따로 .clone() 처리 해주면?
    System.out.println(s1.toString()); //[one, two]
    System.out.println(s2.toString()); //[one, three]  -> deep copy라 원본 s1에 영향을 안줌


    System.out.println("===================================================");


    //case3) 만약 가변객체가 Object가 아니라, 개발자가 만든 객체라면?
    Stack test1 = new Stack(10);
    가변객체 o1 = new 가변객체(1, "a");
    가변객체 o2 = new 가변객체(2, "b");

    test1.push(o1);
    test1.push(o2);

    Stack test2 = test1.clone();

    System.out.println(test1.toString()); //[(value=1, name=a), (value=2, name=b)]
    System.out.println(test2.toString()); //[(value=1, name=a), (value=2, name=b)]


    가변객체 o3 = new 가변객체(3, "c");
    test2.push(o3);

    //그냥 객체 넣으면 deep copy 되네? .clone() 안에  result.elements = elements.clone(); 처리 따로 안해줘도?
    System.out.println(test1.toString()); //[(value=1, name=a), (value=2, name=b)]
    System.out.println(test2.toString()); //[(value=1, name=a), (value=2, name=b), (value=3, name=c)]
  }

}
