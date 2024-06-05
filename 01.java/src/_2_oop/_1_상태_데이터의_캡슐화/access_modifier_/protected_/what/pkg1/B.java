package _2_oop._1_상태_데이터의_캡슐화.access_modifier_.protected_.what.pkg1;

public class B {

  public static void main(String[] args) {
    //step1) 같은 패키치 안에서는 protected 접근 가능하다.
    A a = new A(); //OK
    a.value = 2; //OK
    a.hello(); //OK
  }

}
