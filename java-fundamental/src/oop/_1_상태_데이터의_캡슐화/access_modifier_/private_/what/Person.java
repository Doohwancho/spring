package oop._1_상태_데이터의_캡슐화.access_modifier_.private_.what;

class Person {

  private int value = 1;
  private Person (){} //생성자를 private로 만들면 외부에서 객체 생성 불가

  private void test(){
    System.out.println(this.value); //같은 클래스 안에서만 private 접근 가능
  }

}
