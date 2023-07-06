package access_modifier_.protected_.ex2_factory_method.package2;

import access_modifier_.protected_.ex2_factory_method.package1.Product;

class ConcreteProductB extends Product { //case1) Product가 다른 패키지에 있어도 생성자가 protected라 상속받으면 만들 수 있음
  ConcreteProductB(String name) {
    super(name);
  }

  @Override
  public void use() {
    System.out.println("Using product B: " + name); //case2) name이 protected인데, 다른 패키지여도 자식 클래스니까 접근 가능하다
  }

  @Override
  protected void protectedUse(){
    System.out.println("protected port! name:" + name);
  }

  public void openPortForProtectedUse(){
    protectedUse();
  }
}
