package jdk_versions.jdk8.lambda.step1_why.step4_lambda로_strategy_pattern_간소화;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import jdk_versions.jdk8.lambda.step1_why.Product;

public class Main {

  //Q. step3->4 할 떄 뭐가 바뀜?
  //A. lambda 쓰면 필터용 클래스 따로 안만들어도 됨. 개꿀. 해당 클래스의 true 판별식만 람다로 넣으면 됨.

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


    /************************************************/
    //step1 - lambda쓰면 클래스 생략 가능해!
    //before
//    ArrayList<Product> filteredByName1 = filter(products, new NameFilter("새우깡"));
    //after
    ArrayList<Product> filteredByName2 = filter(products, (Product product) -> product.getName().equals("새우깡"));


    //before
//    ArrayList<Product> filteredByNameAndStore1 = filter(products, new NameAndStoreFilter("곰젤리", "코스트코"));
    //after
    ArrayList<Product> filteredByNameAndStore2 = filter(products,
        (Product product) -> {
          return product.getName().equals("새우깡") && product.getStore().equals("이마트");
    });

    filteredByName2.forEach(x -> System.out.println(x));
    System.out.println("-----------------------------");
    filteredByNameAndStore2.forEach(x -> System.out.println(x));
    System.out.println("================================");

    /************************************************/
    //ex1 - comparator를 lambda로 써보자!

    //sort by price ASC
    Collections.sort(products,
        new Comparator<Product>() {
          @Override
          public int compare(Product proc1, Product proc2) {
            return proc1.getPrice() - proc2.getPrice();
          }
        });
    products.forEach(x -> System.out.println(x));
    System.out.println("-----------------------------");

    //sort by price DESC
    products.sort((Product proc1, Product proc2) -> proc2.getPrice() - proc1.getPrice()); //lambda로 코드 줄일 수 있다!
    products.forEach(x -> System.out.println(x));

    System.out.println("================================");

    /************************************************/
    //ex2 - thread

    //before
    Thread th1 = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("Normal thread!!");
      }
    });


    //after
    Thread th2 = new Thread(() -> System.out.println("Lambda in thread!!"));
  }

}
