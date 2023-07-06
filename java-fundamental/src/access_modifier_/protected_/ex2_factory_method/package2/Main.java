package access_modifier_.protected_.ex2_factory_method.package2;

import access_modifier_.protected_.ex2_factory_method.package1.Product;

//client code
public class Main {
  public static void main(String[] args) {
    ProductFactory factory = new ProductFactory();


    //case2) use()안에서 호출하는 protected name
    Product productA = factory.createProduct("A", "Product A");
    productA.use();

    Product productB = factory.createProduct("B", "Product B");
    productB.use();

    //protected name은 Product가 다른 패키지에 있는데도 불구하고 호출 가능한 이유가, ConcreteProductA,B가 Product의 자식인데, protected는 자식은 접근 허용해주니까. 가능
//    System.out.println(productA.name); //cannot access! protected. 캡슐화 되어있으니, getter/setter인 public method 따로 만들어서 접근하자.



    //case3) use()를 protected로 바꾸면?
    //Main에서 접근 못함. ConcreteProductA 내부에서는 접근할 수 있어도..
//    productA.protectedUse(); //error! not accessable!

    //이 경우, ConcreteProductA에 다른 public method 만들고, 거기에서 protected method를 호출하는 식으로 풀어야 함.
    //왜? encapsulation
    productA.openPortForProtectedUse();
    productB.openPortForProtectedUse();
  }
}
