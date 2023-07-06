package access_modifier_.protected_.what.pkg2;

import access_modifier_.protected_.what.pkg1.A;

public class C {

  public static void main(String[] args) {
    //step2) 다른 패키치에서는 protected 접근 불가능하다.
//    A a = new A(); //fail
//    a.value = 2; //fail
//    a.hello(); //fail
  }
}
