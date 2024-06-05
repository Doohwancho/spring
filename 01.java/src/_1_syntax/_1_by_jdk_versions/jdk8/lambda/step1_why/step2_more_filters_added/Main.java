package _1_syntax._1_by_jdk_versions.jdk8.lambda.step1_why.step2_more_filters_added;


import java.util.ArrayList;
import _1_syntax._1_by_jdk_versions.jdk8.lambda.step1_why.Product;

public class Main {

  //Problem

  //filter의 조건이 수백, 수천, 수만갠데,
  //조건마다 필터 메서드 일일히 만들꺼야?

  //step2) more filters added
  public static ArrayList filterByName(ArrayList<Product> products, String name) {
    ArrayList<Product> filteredProducts = new ArrayList<>();
    for (Product product : products) {
      if (product.getName().equals(name)) {
        filteredProducts.add(product);
      }
    }
    return filteredProducts;
  }

  public static ArrayList<Product> filterByPrice(ArrayList<Product> products, int price) {
    ArrayList<Product> filteredProducts = new ArrayList<>();
    for (Product product : products) {
      if (product.getPrice() <= price) {
        filteredProducts.add(product);
      }
    }
    return filteredProducts;
  }

  public static ArrayList<Product> filterByStoreAndName(
      ArrayList<Product> products, String name, String store) {
    ArrayList<Product> filteredProducts = new ArrayList<>();

    for (Product product : products) {
      if (product.getName().equals(name) && product.getStore().equals(store)) {
        filteredProducts.add(product);
      }
    }

    return filteredProducts;
  }

  public static ArrayList<Product> filterByStoreAndNameAndFood(
      ArrayList<Product> products, String name, String store, boolean isFood) {
    ArrayList<Product> filteredProducts = new ArrayList<>();

    for (Product product : products) {
      if (product.getName().equals(name)
          && product.getStore().equals(store)
          && product.isFood() == isFood) {
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


    ArrayList<Product> filteredByName = filterByName(products, "새우깡");
    filteredByName.forEach(x -> System.out.println(x.toString()));
    System.out.println("--------------------------");

    ArrayList<Product> filteredByPrice = filterByPrice(products, 1200);
    filteredByPrice.forEach(x -> System.out.println(x.toString()));
    System.out.println("--------------------------");

    ArrayList<Product> filteredByStoreAndName = filterByStoreAndName(products, "자갈치", "홈플러스");
    filteredByStoreAndName.forEach(x -> System.out.println(x.toString()));
    System.out.println("--------------------------");

    ArrayList<Product> filteredByStoreAndNameAndFood = filterByStoreAndNameAndFood(products, "곰젤리", "코스트코", true);
    filteredByStoreAndNameAndFood.forEach(x -> System.out.println(x.toString()));
    System.out.println("--------------------------");

  }

}
