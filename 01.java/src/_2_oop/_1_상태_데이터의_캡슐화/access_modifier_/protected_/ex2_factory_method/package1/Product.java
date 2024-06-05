package _2_oop._1_상태_데이터의_캡슐화.access_modifier_.protected_.ex2_factory_method.package1;

public abstract class Product {
  protected String name; //protected로 정의하면, 자식에서 직접 접근 가능. 혹은 @Override로 재정의 하거나, 기능 확장 가능.

  protected Product(String name) { //생성자에 protected를 붙인 경우, 다른 패키지에서 상속을 허용했다는 것. 만약 상속 허용 안하고 싶으면 private constructor나 final class 사용한다.
    this.name = name;
  }

  public abstract void use();

  protected abstract void protectedUse();

  public abstract void openPortForProtectedUse();

}
