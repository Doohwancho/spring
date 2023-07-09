package clean_code._01_principle_.rule_of_6;


import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Q. what is rule of 6?
//A. A line of code containing 6+ pieces of information should be simplified.
//   왜? 이해하기 어려우니까.
//   breaking down complex lines of code into simpler ones to reduce cognitive load and improve code readability
//
// ex. One line, one task.
public class Main {

  public static void main(String[] args) {
    //case1)
    //don't
    int x, y = 2;

    //do
    int a = 2;
    int b = 2;

    //case2)
    String inputString = "asldk=fjasAaSDFASDF&ASDJFKA=LSDFJ&ASLDJ=FKASDF";

    //don't
    String result = Arrays.stream(inputString.split("&")).map(s -> s.split("=")[1]).collect(Collectors.joining(","));

    //do
    String[] splitInput = inputString.split("&");
    Stream<String> streamInput = Arrays.stream(splitInput);
    Function<String, String> afterEquals = s -> s.split("=")[1];
    Stream<String> mappedStream = streamInput.map(afterEquals);
    String result2 = mappedStream.collect(Collectors.joining(","));


    //근데 이건 옳지 못한 예제인 듯.. 위에꺼가 보기 더 편함..
  }

}
