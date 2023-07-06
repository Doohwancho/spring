package jdk.jdk8.stream._04_stream_고급;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
  private static long counter;
  private static void wasCalled() {
    counter++;
  }

  //Null-safe stream 생성하기
  //feature: null을 인풋으로 받으면 NPE가 아니라 []를 리턴해줌.
  public static <T> Stream<T> collectionToStream(Collection<T> collection) {
    return Optional
        .ofNullable(collection)
        .map(Collection::stream)
        .orElseGet(Stream::empty);
  }

  public static void main(String[] args) {

    /*******************************/
    //step1. 동작 순서
    List<String> list = Arrays.asList("Eric", "Elena", "Java");

    Optional<String> first = list.stream()
        .filter(el -> {
          System.out.println("filter() was called.");
          return el.contains("a");
        })
        .map(el -> {
          System.out.println("map() was called.");
          return el.toUpperCase();
        })
        .findFirst();

    System.out.println(first.get()); //ELENA

    //console.log
//    filter() was called.
//    filter() was called.
//    map() was called.

    //Q. 왜 filter()이 두번 호출됬지?

    //동작 순서
//    1. 처음 요소인 “Eric” 은 “a” 문자열을 가지고 있지 않기 때문에 다음 요소로 넘어갑니다. 이 때 “filter() was called.” 가 한 번 출력됩니다.
//    2. 다음 요소인 “Elena” 에서 "filter() was called."가 한 번 더 출력됩니다. "Elena"는 "a"를 가지고 있기 때문에 다음 연산으로 넘어갈 수 있습니다.
//    3. 다음 연산인 map 에서 toUpperCase 메소드가 호출됩니다. 이 때 "map() was called"가 출력됩니다.
//    4. 마지막 연산인 findFirst 는 첫 번째 요소만을 반환하는 연산입니다. 따라서 최종 결과는 “ELENA” 이고 다음 연산은 수행할 필요가 없어 종료됩니다.


    //takeaway

    //1. filter() 다 끝난 다음 map()으로 넘어갈 줄 알았는데, 개별 element 별로 수평으로 진행되네?
    //2. 1번 때문에 .findFirst()같은 애들이 빛을 바라내?


    System.out.println("========================================");
    /*******************************/
    //step2. 성능 향상

    list = Arrays.asList("Eric", "Elena", "Java");

//    List<String> result1 = list.stream()
//        .map(el -> {
//          wasCalled();
//          return el.substring(0, 3);
//        })
//        .skip(2)
//        .collect(Collectors.toList());
//
//    System.out.println(counter); // 3
//    System.out.println(result1); //[Jav]


    list = Arrays.asList("Eric", "Elena", "Java");
    List<String> result2 = list.stream()
        .skip(2) //skip으로 앞에 불필요한 연산 몇개 스킵해서 최적화 가능. 말고도 filter, distinct 먼저 써서 쓸 수 있음.
        .map(el -> {
          wasCalled();
          return el.substring(0, 3);
        })
        .collect(Collectors.toList());

    System.out.println(counter); // 1
    System.out.println(result2); // [Jav]



    //실행순서?

    //1. Eric 때 wasCalled() 하고, .substring() 한 후, skip 함
    //2. Elena도 같음
    //3. Java도 .substring()까지 한 후, cnlgkqgka.

    System.out.println("========================================");



    /*******************************/
    //step3. 스트림 재사용

    Stream<String> stream = java.util.stream.Stream.of("Eric", "Elena", "Java")
            .filter(name -> name.contains("a"));

    //문제!
    Optional<String> firstElement1 = stream.findFirst();
//    Optional<String> anyElement = stream.findAny(); // IllegalStateException: stream has already been operated upon or closed.
    //findFirst 메소드를 실행하면서 스트림이 닫히기 때문에 findAny 하는 순간 런타임 예외(runtime exception)이 발생!


    //해결법
    List<String> names =
        Stream.of("Eric", "Elena", "Java")
            .filter(name -> name.contains("a"))
            .collect(Collectors.toList()); //.collect()로 저장해 놓고, .stream()으로 언제든지 꺼내쓸 수 있음.

    Optional<String> firstElement2 = names.stream().findFirst();
    Optional<String> anyElement = names.stream().findAny();

    System.out.println("========================================");



    /*******************************/
    //step4. 지연 처리(lazy invocation)

    //문제
    list = Arrays.asList("Eric", "Elena", "Java");
    counter = 0;
    Stream<String> stream2 = list.stream().filter(el -> {
          wasCalled();
          return el.contains("a");
        });
    System.out.println(counter); // 0 ?? -> 왜냐하면 최종 작업이 실행되지 않아서 실제로 스트림의 연산이 실행되지 않았기 때문입니다.


    //해결법
    list.stream().filter(el -> {
      wasCalled();
      return el.contains("a");
    }).collect(Collectors.toList());
    System.out.println(counter); // 3 -> 최종연산인 .collect()가 실행됬기 때문에, wasCalled()가 적용됬다.


    System.out.println("========================================");



    /*******************************/
    //step5. null-safe 스트림 생성하기

    List<Integer> intList = Arrays.asList(1, 2, 3);
    List<String> strList = Arrays.asList("a", "b", "c");

    Stream<Integer> intStream = collectionToStream(intList); // [1, 2, 3]
    Stream<String> strStream = collectionToStream(strList); // [a, b, c]

    List<String> nullList = null;

    //문제
//    nullList.stream()
//        .filter(str -> str.contains("a"))
//        .map(String::length)
//        .forEach(System.out::println); // NPE!


    //해결법
    collectionToStream(nullList)
        .filter(str -> str.contains("a"))
        .map(String::length)
        .forEach(System.out::println); // []

    //null을 인풋으로 받으면 NPE가 아니라 []를 리턴해줌.


    System.out.println("========================================");



    /*******************************/
    //step6. 줄여쓰기 (simplified)

    //아 이게 다 .stream()이 생략된 애들이었구나~

    /*
    collection.stream().forEach()
  → collection.forEach()

collection.stream().toArray()
  → collection.toArray()

Arrays.asList().stream()
  → Arrays.stream() or Stream.of()

Collections.emptyList().stream()
  → Stream.empty()

stream.filter().findFirst().isPresent()
  → stream.anyMatch()

stream.collect(counting())
  → stream.count()

stream.collect(maxBy())
  → stream.max()

stream.collect(mapping())
  → stream.map().collect()

stream.collect(reducing())
  → stream.reduce()

stream.collect(summingInt())
  → stream.mapToInt().sum()

stream.map(x -> {...; return x;})
  → stream.peek(x -> ...)

!stream.anyMatch()
  → stream.noneMatch()

!stream.anyMatch(x -> !(...))
  → stream.allMatch()

stream.map().anyMatch(Boolean::booleanValue)
  → stream.anyMatch()

IntStream.range(expr1, expr2).mapToObj(x -> array[x])
  → Arrays.stream(array, expr1, expr2)

Collection.nCopies(count, ...)
  → Stream.generate().limit(count)

stream.sorted(comparator).findFirst()
  → Stream.min(comparator)
     */

    System.out.println("========================================");
  }

}
