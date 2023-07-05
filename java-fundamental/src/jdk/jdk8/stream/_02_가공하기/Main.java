package jdk.jdk8.stream._02_가공하기;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

  public static void main(String[] args) {
    //part2. 가공하기

    List<String> names = Arrays.asList("Eric", "Elena", "Java");

    /****************************************/
    //step1. filter()

    //Stream<T> filter(Predicate<? super T> predicate);
    Stream<String> stream1 = names.stream().filter(name -> name.contains("a")); // [Elena, Java]


    /****************************************/
    //step2. map()

    //<R> Stream<R> map(Function<? super T, ? extends R> mapper);
    //lambda를 인자로 받고 map한 후, Stream을 반환
    Stream<String> stream2 = names.stream().map(String::toUpperCase); // [ERIC, ELENA, JAVA]


    /****************************************/
    //step3. flatmap()
    List<List<Integer>> listOfLists = List.of(List.of(1, 2), List.of(3, 4));

    List<List<Integer>> transformed = listOfLists.stream()
        .map(list -> list.stream().map(i -> i * i).collect(Collectors.toList()))
        .collect(Collectors.toList()); // Result: [[1, 4], [9, 16]]


    //<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
    List<Integer> flattened = listOfLists.stream()
        .flatMap(list -> list.stream().map(i -> i * i))
        .collect(Collectors.toList()); // Result: [1, 4, 9, 16]

    //flat은 차원 축소할 때 쓰는 놈인 듯?





    /****************************************/
    //step4. sort()
    IntStream.of(14, 11, 20, 39, 23)
        .sorted()
        .boxed()
        .collect(Collectors.toList());

    List<String> lang =
        Arrays.asList("Java", "Scala", "Groovy", "Python", "Go", "Swift");

    lang.stream()
        .sorted()
        .collect(Collectors.toList()); // [Go, Groovy, Java, Python, Scala, Swift]

    //Comparator를 인자로 넘길 수 있다.
    lang.stream()
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList()); // [Swift, Scala, Python, Java, Groovy, Go]

    lang.stream()
        .sorted(Comparator.comparingInt(String::length))
        .collect(Collectors.toList()); // [Go, Java, Scala, Swift, Groovy, Python]

    lang.stream()
        .sorted((s1, s2) -> s2.length() - s1.length())
        .collect(Collectors.toList()); // [Groovy, Python, Scala, Swift, Java, Go]



    /****************************************/
    //step4. iterating

    //stream 내 개별 element에 연산하는 것

    int sum = IntStream.of(1, 3, 5, 7, 9)
        .peek(System.out::println)
        .sum();

    //‘peek’ 은 그냥 확인해본다는 단어 뜻처럼 특정 결과를 반환하지 않는 함수형 인터페이스 Consumer 를 인자로 받습니다.
    // 따라서 스트림 내 요소들 각각에 특정 작업을 수행할 뿐 결과에 영향을 미치지 않습니다.
    // 다음처럼 작업을 처리하는 '중간에 결과를 확인해볼 때' 사용할 수 있습니다.


  }

}
