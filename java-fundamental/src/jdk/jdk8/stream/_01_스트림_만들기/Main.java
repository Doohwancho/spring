package jdk.jdk8.stream._01_스트림_만들기;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Main {
  public static void main(String[] args) {

    //part1. 스트림 생성

    //step1. array -> Stream
    String[] arr = new String[]{"a", "b", "c"};
    Stream<String> arrStream1 = Arrays.stream(arr);
    Stream<String> streamOfArrayPart = Arrays.stream(arr, 1, 3); // 1~2 요소 [b, c]

//    arrStream.forEach(x -> System.out.println(x));


    /****************************************/
    //step2. Stream.builder()
    Stream<String> builderStream = Stream.<String>builder()
              .add("Eric")
              .add("Elena")
              .add("Java")
              .build(); // [Eric, Elena, Java]

//    builderStream.forEach(x -> System.out.println(x));


    /****************************************/
    //step3. list -> Stream
    List<String> arrList = Arrays.asList("a", "b", "c");
    Stream<String> streamList = arrList.stream();

//    streamList.forEach(x -> System.out.println(x));


    /****************************************/
    //step4. 기본 타입형 & String

    IntStream intStream = IntStream.range(1, 5); // [1, 2, 3, 4]
    Stream<Integer> boxedIntStream = IntStream.range(1, 5).boxed(); //int -> Integer로 boxing 가능

    LongStream longStream = LongStream.rangeClosed(1, 5); // [1, 2, 3, 4, 5]
    DoubleStream doubles = new Random().doubles(3); // 난수 3개 생성

    IntStream charsStream = "Stream".chars(); // [83, 116, 114, 101, 97, 109]



    /****************************************/
    //step5. Stream.generate()
    Stream<String> generatedStream = Stream.generate(() -> "gen").limit(5); //stream size가 무한하기 때문에 크기 제한해야 한다.

    /*
      .generate()의 인자로 Supplier<T> 넣을 수 있다.
      Supplier<>는 리턴값만 있는 함ㅅ무형 인터페이스
     */

//    generatedStream.forEach(x -> System.out.println(x));// [el, el, el, el, el]




    /****************************************/
    //step6. Stream.iterate()
    Stream<Integer> iteratedStream = Stream.iterate(30, n -> n + 2).limit(5);

//    iteratedStream.forEach(x -> System.out.println(x)); // [30, 32, 34, 36, 38]


    /****************************************/
    // step7. File
    Stream<String> stringStream = Pattern.compile(", ").splitAsStream("Eric, Elena, Java"); // [Eric, Elena, Java]
//    Stream<String> lineStream = Files.lines(Paths.get("file.txt"), Charset.forName("UTF-8"));


    /****************************************/
    //Step8. 병렬 stream
    //String[] -> 병렬 스트림
    String[] stringList = new String[]{"a", "b", "c"};

    Stream<String> parallelStream = Arrays.stream(stringList).parallel(); // 병렬 처리 스트림
//    parallelStream.forEach(x -> System.out.println(x));

    /****************************************/
    //Step9. stream 연결하기
    Stream<String> stream1 = Stream.of("Java", "Scala", "Groovy");
    Stream<String> stream2 = Stream.of("Python", "Go", "Swift");
    Stream<String> concat = Stream.concat(stream1, stream2); // [Java, Scala, Groovy, Python, Go, Swift]

  }
}
