package _1_syntax._1_by_jdk_versions.jdk8.stream;

public class Product {

  String name;
  int price;
  public Product(int price, String name) {
    this.price = price;
    this.name = name;
  }

  public String getName(){
    return this.name;
  }


  public int getAmount() {
    return this.price;
  }
}

