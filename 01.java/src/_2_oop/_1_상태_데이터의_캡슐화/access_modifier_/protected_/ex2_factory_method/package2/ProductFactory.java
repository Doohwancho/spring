package _2_oop._1_상태_데이터의_캡슐화.access_modifier_.protected_.ex2_factory_method.package2;

import _2_oop._1_상태_데이터의_캡슐화.access_modifier_.protected_.ex2_factory_method.package1.Product;
// Factory class
class ProductFactory {
  public Product createProduct(String type, String name) {
    if (type.equals("A")) {
      return new ConcreteProductA(name);
    } else if (type.equals("B")) {
      return new ConcreteProductB(name);
    } else {
      return null;
    }
  }
}
