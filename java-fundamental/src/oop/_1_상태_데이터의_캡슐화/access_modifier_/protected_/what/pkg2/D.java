package oop._1_상태_데이터의_캡슐화.access_modifier_.protected_.what.pkg2;

import oop._1_상태_데이터의_캡슐화.access_modifier_.protected_.what.pkg1.A;

public class D extends A {
  public D (){
    //step3) 다른 패키지 이나, 자식인 경우, protected는 접근 가능!
    super(); //OK - 다른 패키지어도, 자식이면 접근 가능하다.
    this.value = 2; //OK - 다른 패키지어도, 자식이면 접근 가능하다.
    this.hello(); //OK - 다른 패키지어도, 자식이면 접근 가능하다.
  }
}
