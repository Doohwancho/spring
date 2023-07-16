package com.tdd.tddTest.jqwik.helloworld;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.jqwik.api.Arbitraries;
import net.jqwik.api.Arbitrary;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Provide;
import net.jqwik.api.Report;
import net.jqwik.api.Reporting;

public class ListReverseProperties {
  //case1) reverse()
  @Property
  boolean reverseTwiceIsOriginal(@ForAll List<Integer> original) {
    return reverse(reverse(original)).equals(original);
  }

  //  @Report(Reporting.GENERATED)
  @Property
  boolean reverseWithWildcardType(@ForAll List<?> original) { //? -> 모든 타입에 대해 체크한다.
    return reverse(reverse(original)).equals(original);
  }

  private <T> List<T> reverse(List<T> original) {
    List<T> clone = new ArrayList<>(original);
    Collections.reverse(clone);
    return clone;
  }


  //case2) join()
  @Property
  boolean joiningTwoLists(
      @ForAll List<String> list1,
      @ForAll List<String> list2
  ) {
    List<String> joinedList = new ArrayList<>(list1);
    joinedList.addAll(list2);
    return joinedList.size() == list1.size() + list2.size();
  }
}
