package clean_code._04_function_.if_.step3_역조건;

public class Main {

  //Don't
  public String someMethod1(boolean condition) {
    if(condition){
      //한참 많은 코드
      return "world";
    }
    // 적은 코드     -> 문제: 안보일 수도 있다.
    return "hello";
  }

  //Do
  public String someMethod2(boolean condition) {
    if(!condition){
      //적은 코드
      return "hello";
    }
    //한참 많은 코드
    return "world";
  }

}
