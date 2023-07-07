package oop.access_modifier_.protected_.ex2_factory_method.package2;

import oop.access_modifier_.protected_.ex2_factory_method.package1.Product;
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
