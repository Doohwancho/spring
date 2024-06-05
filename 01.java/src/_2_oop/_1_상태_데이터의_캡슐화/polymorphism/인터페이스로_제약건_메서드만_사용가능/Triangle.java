package _2_oop._1_상태_데이터의_캡슐화.polymorphism.인터페이스로_제약건_메서드만_사용가능;

class Triangle implements IDraw {

  private void init(){
    System.out.println("init Triangle");
  }

  public void make(){
    System.out.println("make triangle!");
  }

  @Override
  public void draw() {
    System.out.println("draw triangle!");
  }
}
