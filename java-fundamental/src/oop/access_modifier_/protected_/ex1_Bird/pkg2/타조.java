package oop.access_modifier_.protected_.ex1_Bird.pkg2;

import oop.access_modifier_.protected_.ex1_Bird.pkg1.Bird;

public class 타조 extends Bird {

  void run(){
    System.out.println("I am running");
  }

  @Override
  protected void moveFast(){
    run(); //다른 새들과는 다르게, 타조는 빠르게 움직일 때 fly()하지 않고 run() 한다!
    //상속받은 자식 입장에서 변경 가능성이 있어! 이점 유의해! 라고 알려주는 키워드가 protected!
  }

  public static void main(String[] args) {
    타조 obj = new 타조();
    obj.moveFast();
  }
}
