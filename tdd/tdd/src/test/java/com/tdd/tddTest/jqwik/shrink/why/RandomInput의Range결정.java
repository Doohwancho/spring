package com.tdd.tddTest.jqwik.shrink.why;

import net.jqwik.api.Disabled;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.ShrinkingMode;
import net.jqwik.api.constraints.Positive;

public class RandomInput의Range결정 {

  //Q. why need shrunk?
  //A. random input의 범위를 조절할 필요가 종종 있다.


  //step1)
  @Disabled("2258378 에서 막힌다! anInt의 범위를 줄여야 한다!")
  @Property(shrinking = ShrinkingMode.OFF)
  boolean rootOfSquareShouldBeOriginalValue1(@Positive @ForAll int anInt) {
    int square = anInt * anInt;
    return Math.sqrt(square) == anInt;
  }

  //step2)
  @Disabled("46341에서 막힌다! 근데 범위가 매우 줄어들었다! 이게 fail하는 최솟값! 이걸 anInt의 max bound로 정하면 된다!")
  @Property(shrinking = ShrinkingMode.FULL)
  boolean rootOfSquareShouldBeOriginalValue2(@Positive @ForAll int anInt) {
    int square = anInt * anInt;
    return Math.sqrt(square) == anInt;
  }


}
