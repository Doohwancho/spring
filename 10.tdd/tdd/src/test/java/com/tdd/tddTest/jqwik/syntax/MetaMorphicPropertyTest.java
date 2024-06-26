package com.tdd.tddTest.jqwik.syntax;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

public class MetaMorphicPropertyTest {
  private <T> List<T> reverse(List<T> original) {
    List<T> clone = new ArrayList<>(original);
    Collections.reverse(clone);
    return clone;
  }

  private <T> List<T> concat(List<T> first, List<T> second) {
    List<T> clone = new ArrayList<>(first);
    clone.addAll(second);
    return clone;
  }

  @Property
  boolean reverseConcatenatedLists(
      @ForAll List<Integer> first,
      @ForAll List<Integer> second
  ) {
    List<Integer> firstReversed = reverse(first);
    List<Integer> secondReversed = reverse(second);

    List<Integer> reversed = reverse(concat(first, second));

    return reversed.equals(concat(secondReversed, firstReversed));
  }
}
