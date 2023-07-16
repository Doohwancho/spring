package com.tdd.tddTest.jqwik.syntax.parameter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;

public class CustomMethodAsParameter {
  //case1)
  @Property
  void letsGenerateGermanZipCodes(@ForAll("germanZipCodes") String zipCode) {
  }

  @Provide
  Arbitrary<String> germanZipCodes() {
    return Arbitraries.strings().withCharRange('0', '9').ofLength(5);
  }

  //case2)
  @Property
  boolean every_third_element_starts_with_Fizz(@ForAll("divisibleBy3") int i) {
    return fizzBuzz().get(i - 1).startsWith("Fizz");
  }

  @Provide
  Arbitrary<Integer> divisibleBy3() {
    return Arbitraries.integers().between(1, 100).filter(i -> i % 3 == 0);
  }

  private List<String> fizzBuzz() {
    return IntStream.range(1, 100).mapToObj((int i) -> {
      boolean divBy3 = i % 3 == 0;
      boolean divBy5 = i % 5 == 0;

      return divBy3 && divBy5 ? "FizzBuzz"
          : divBy3 ? "Fizz"
              : divBy5 ? "Buzz"
                  : String.valueOf(i);
    }).collect(Collectors.toList());
  }
}
