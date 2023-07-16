package com.tdd.tddTest.jqwik.how_to_find_property.induction;

import java.util.List;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

public class SortListOfIntegers {
  /*
    dynamic programming 처럼
    proof시, 전체를 부분으로 쪼갠 다음,
    연역법으로 풀어가는 방식 
   */
  @Property
  boolean sortingAListWorks(@ForAll List<Integer> unsorted) {
    return isSorted(sort(unsorted));
  }

  private boolean isSorted(List<Integer> sorted) {
    if (sorted.size() <= 1) return true;
    return sorted.get(0) <= sorted.get(1)
        && isSorted(sorted.subList(1, sorted.size()));
  }

  private List<Integer> sort(List<Integer> unsorted) {
    unsorted.sort((a, b) -> a > b ? 1 : -1);
    return unsorted;
  }
}
