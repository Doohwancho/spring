package oop.access_modifier_.protected_.ex1_Bird.pkg1;

public class Bird {
  void fly() { //Bird를 상속받는 모든 새 종류는 fly()가 무조건 있어야 하니까, 이건 private_package 로 남김.
    System.out.println("I am flying");
  }


  //Q. 왜 moveFast()를 protected 처리함?
  //A. 모든 새는 무조건 fly()하는건 맞음 확실하고 변하지 않음.
  //   근데 모든 새가 moveFast()할 떄 fly()만 하진 않을거잖음? 어떤 새는 달릴거고, 어떤 새는 다이브 할거고 등..
  //   moveFast()는 일반적으로는 fly()가 보편적이라, 부모에서 fly()라고 처리했긴 했지만,
  //   간혹가다가 타조는 moveFast()시 fly()대신 run()해야 하고, 어떤 새는 dive() 해야 하니까,
  //   이 메서드는 '변경 가능성'이 있으니, @Override에 유의해서 쓰라는 개발자에게 알려주는 키워드가 protected 임.
  protected void moveFast(){
    fly();
  }

}
