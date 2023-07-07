package jdk_versions.jdk8.lambda.step1_why.step3_strategy_pattern;


import java.util.ArrayList;
import jdk_versions.jdk8.lambda.step1_why.Product;

public class Main {

  //step2에서 수백, 수천개 필터 메서드 들을 일일이 추가해주는 번거로움을 strategy pattern으로 해결했다!

  //strategy pattern을 사용함으로써 filter함수는 변하지 않습니다.
  //또한 새로운 요구사항이 추가되면 FilterPredicate interface를 구현하는 새로운 요구사항 클래스를 추가하면 됩니다.

  public static ArrayList<Product> filter(ArrayList<Product> products, FilterPredicate filterInterface) {
    ArrayList<Product> filteredProducts = new ArrayList<>();

    for (Product product : products) {
      if(filterInterface.filter(product)) {
        filteredProducts.add(product);
      }
    }

    return filteredProducts;
  }
  public static void main(String[] args) {
    ArrayList<Product> products = new ArrayList<>();
    products.add(new Product("새우깡", 1200, true, "농심", "이마트"));
    products.add(new Product("감자깡", 1200, true, "농심", "이마트"));
    products.add(new Product("양파링", 1000, true, "농심", "홈플러스"));
    products.add(new Product("고구마칩", 3000, true, "오리온", "홈플러스"));
    products.add(new Product("자갈치", 800, true, "오리온", "홈플러스"));
    products.add(new Product("가위", 4000, false, "문방구", "코스트코"));
    products.add(new Product("청소기", 70000, false, "LG", "코스트코"));
    products.add(new Product("양주", 30000, true, "진로", "코스트코"));
    products.add(new Product("곰젤리", 4000, true, "Bear", "코스트코"));

    ArrayList<Product> filteredByName = filter(products, new NameFilter("새우깡"));
    filteredByName.forEach(x -> System.out.println(x.toString()));
    System.out.println("---------------------------------");

    ArrayList<Product> filteredByPrice = filter(products, new NameAndStoreFilter("곰젤리", "코스트코"));
    filteredByPrice.forEach(x -> System.out.println(x.toString()));
  }

}
