package com.tdd.tddTest.jqwik.shrink.what;

import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.Disabled;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;

public class Main {

  //case1)
  @Disabled("shrunk으로 fail-case 중 가장 작은 101을 도출한다.")
  @Property
  boolean shouldShrinkTo101(@ForAll("numberStrings") String aNumberString) {
    return Integer.parseInt(aNumberString) % 2 == 0;
  }

  @Provide
  Arbitrary<String> numberStrings() {
    return Arbitraries.integers().between(100, 10000).map(String::valueOf);
  }

}
