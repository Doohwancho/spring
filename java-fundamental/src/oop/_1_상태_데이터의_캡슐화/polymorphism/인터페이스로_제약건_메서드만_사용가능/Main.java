package oop._1_상태_데이터의_캡슐화.polymorphism.인터페이스로_제약건_메서드만_사용가능;

//interface를 사용하면, 필요한 public function만 볼 수 있다. (refactoring시 장점)
public class Main {
  public static void main(String[] args) {
    IDraw drawer = new Triangle();
    drawer.draw();
//    drawer.make(); //error! -> IDraw로 선언했기 때문에, Triangle에 있긴 한데 IDraw에서 implements 안받은 다른 메서드들은 사용 불가 -> 몇 천줄, 디버깅할 떄, 봐햐아는 함수 갯수 줄여줌 개꿀.
    //Q. 만약 Triangle.draw() 함수에 문제가 터졌다?
    //-> 원래대로라면, 몇천줄의 Triangle 클래스 다 분석했어야 했는데,
    //-> 인터페이스로 draw() 만 쓸 수 있게 제약걸었으니까, Triangle 클래스 안 다른 코드들 몇천줄 분석할 필요없이, 딱 draw() 메서드만 보면 됨.
    //-> 인터페이스 활용한 다형성의 장점1: make()이 public 함수긴 한데, IDraw 인터페이스에 포함된 함수가 아니라 안봐도 됨 -> 디버깅/코드 분석시 개꿀

    IDraw drawer2 = new Rectangle();
    drawer2.draw();
    //인터페이스 활용한 다형성의 장점2:
    //Triangle이랑 비슷한 Rectangle 클래스 만들고 싶으면,
    //Triangle 클래스 다 분석할 필요 없이, IDraw에 붙은 public 메서드만 잘 작동하는지 확인되면,
    //나머지 코드는 복붙만 해도 됨.
  }

}
