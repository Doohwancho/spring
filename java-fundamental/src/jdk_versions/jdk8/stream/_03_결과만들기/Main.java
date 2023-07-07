package jdk_versions.jdk8.stream._03_결과만들기;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import jdk_versions.jdk8.stream.Person;
import jdk_versions.jdk8.stream.Product;

public class Main {

  public static void main(String[] args) {
    //part3. 결과 만들기
    List<Person> personList = new ArrayList<>();
    personList.add(new Person("짱구", 23, "010-1234-1234"));
    personList.add(new Person("유리", 24, "010-2341-2341"));
    personList.add(new Person("철수", 29, "010-3412-3412"));
    personList.add(new Person("맹구", 25, null));


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



    //ex) reduce
    List<Integer> list = List.of(5, 4, 2, 1, 6, 7, 8, 3);

    Integer result = list.stream()
        .reduce(0, (value1, value2) -> value1 + value2);// 36

    int intResult = list.stream()
        // 또는 .mapToInt(x -> x).sum();
        .mapToInt(Integer::intValue).sum(); //이렇게 하면 박싱 비용 줄일 수 있다.

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


    IntSummaryStatistics statistics =
        productList.stream()
            .collect(Collectors.summarizingInt(Product::getAmount)); // IntSummaryStatistics {count=5, sum=86, min=13, average=17.200000, max=23}

    Map<Integer, List<Product>> collectorMapOfLists =
        productList.stream()
            .collect(Collectors.groupingBy(Product::getAmount));

    /*
      {23=[Product{amount=23, name='potatoes'},
      Product{amount=23, name='bread'}],
      13=[Product{amount=13, name='lemon'},
      Product{amount=13, name='sugar'}],
      14=[Product{amount=14, name='orange'}]}
     */

    Map<Boolean, List<Product>> mapPartitioned =
        productList.stream()
            .collect(Collectors.partitioningBy(el -> el.getAmount() > 15));

    /*
    {false=[Product{amount=14, name='orange'},
        Product{amount=13, name='lemon'},
        Product{amount=13, name='sugar'}],
 true=[Product{amount=23, name='potatoes'},
       Product{amount=23, name='bread'}]}
     */

    Set<Product> unmodifiableSet =
        productList.stream()
            .collect(Collectors.collectingAndThen(Collectors.toSet(), //Set 으로 collect 한 후
                Collections::unmodifiableSet)); //수정불가한 Set 으로 변환하는 작업


    Collector<Product, ?, LinkedList<Product>> toLinkedList =
        Collector.of(LinkedList::new,
            LinkedList::add,
            (first, second) -> {
              first.addAll(second);
              return first;
            });

    LinkedList<Product> linkedListOfPersons = productList.stream().collect(toLinkedList); //위 코드와 동일


    //
    Map<String, Person> personMap = personList.stream().collect(Collectors.toMap(Person::getName, Function.identity())); // Function.identity는 t -> t, 항상 입력된 인자(자신)를 반환합니다.


    //same thing with above
    Map<String, Person> personMap2 = personList.stream()
        .collect(Collectors.toMap(new Function<Person, String>() {
          @Override
          public String apply(Person person) {
            return person.getName();
          }
        }, new Function<Person, Person>() {
          @Override
          public Person apply(Person person) {
            return person;
          }
        }));



    /****************************************/
    //step4. matching

    List<String> names = Arrays.asList("Eric", "Elena", "Java");

    boolean anyMatch = names.stream()
        .anyMatch(name -> name.contains("a"));
    boolean allMatch = names.stream()
        .allMatch(name -> name.length() > 3);
    boolean noneMatch = names.stream()
        .noneMatch(name -> name.endsWith("s"));



    /****************************************/
    //step5. iterating

    names.stream().forEach(System.out::println);

  }

}
