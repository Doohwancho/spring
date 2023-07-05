package jdk.jdk8.stream._03_결과만들기;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import jdk.jdk8.stream.Product;

public class Main {

  public static void main(String[] args) {
    //part3. 결과 만들기


    /****************************************/
    //step1. calculating

    long count = IntStream.of(1, 3, 5, 7, 9).count();
    long sum = LongStream.of(1, 3, 5, 7, 9).sum();

    OptionalInt min = IntStream.of(1, 3, 5, 7, 9).min();
    OptionalInt max = IntStream.of(1, 3, 5, 7, 9).max();

    DoubleStream.of(1.1, 2.2, 3.3, 4.4, 5.5)
        .average()
        .ifPresent(System.out::println);

    /****************************************/
    //step2. reduction

    //reduce()는 3가지 파라미터를 받을 수 있다.
    //1. accumulator : 각 요소를 처리하는 계산 로직. 각 요소가 올 때마다 중간 결과를 생성하는 로직.
    //2. identity : 계산을 위한 초기값으로 스트림이 비어서 계산할 내용이 없더라도 이 값은 리턴.
    //3. combiner : 병렬(parallel) 스트림에서 나눠 계산한 결과를 하나로 합치는 동작하는 로직.


    //case1) 인자가 1개인 경우
    OptionalInt reducedOneParam = IntStream.range(1, 4) // [1, 2, 3]
            .reduce((a, b) -> {
              return Integer.sum(a, b);
            }); // 6


    //case2) 인자가 2개인 경우
    int reducedTwoParams = IntStream.range(1, 4) // [1, 2, 3]
            .reduce(10, Integer::sum); // method reference, -> 16


    //case3) 인자를 3개받는 경우 (parallel stream에서만 동작함. 왜? 3. combiner는 병렬 스트림을 합치는 애라서.)
    Integer reducedThreeParams = Stream.of(1, 2, 3)
        .parallel()
        .reduce(10, // identity
            Integer::sum, // accumulator -> [11,12,13]
            (a, b) -> {
              System.out.println("combiner was called");
              return a + b;
            }); //36


    /****************************************/
    //step3. collecting

    List<Product> productList =
        Arrays.asList(new Product(23, "potatoes"),
            new Product(14, "orange"),
            new Product(13, "lemon"),
            new Product(23, "bread"),
            new Product(13, "sugar"));


    List<String> collectorCollection =
        productList.stream()
            .map(Product::getName)
            .collect(Collectors.toList()); // [potatoes, orange, lemon, bread, sugar]

//    collectorCollection.stream().forEach(x -> System.out.println(x));

    String listToString1 =
        productList.stream()
            .map(Product::getName)
            .collect(Collectors.joining()); // potatoesorangelemonbreadsugar

//    System.out.println(listToString1);

    String listToString2 =
        productList.stream()
            .map(Product::getName)
            .collect(Collectors.joining(", ", "<", ">")); // <potatoes, orange, lemon, bread, sugar>

    Double averageAmount =
        productList.stream()
            .collect(Collectors.averagingInt(Product::getAmount)); // 17.2


    Integer summingAmount1 =
        productList.stream()
            .collect(Collectors.summingInt(Product::getAmount)); // 86

    Integer summingAmount2 =
        productList.stream()
            .mapToInt(Product::getAmount)
            .sum(); // 86




    /****************************************/
    //step4. matching

    /****************************************/
    //step5. iterating
  }

}
