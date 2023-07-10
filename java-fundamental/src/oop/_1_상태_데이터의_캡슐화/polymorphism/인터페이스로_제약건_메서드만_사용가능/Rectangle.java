package oop._1_상태_데이터의_캡슐화.polymorphism.인터페이스로_제약건_메서드만_사용가능;

class Rectangle implements IDraw{
  private void init(){
    System.out.println("init Rectangle");
  }
  public void make(){
    System.out.println("make rectangle!");
  }

  @Override
  public void draw() {
    System.out.println("draw rectangle!");
  }
}
