package com.tdd.tddTest.jqwik.syntax.parameter;

import java.util.ArrayList;
import java.util.List;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;

public class ManyParams {
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
