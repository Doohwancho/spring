package com.tdd.tddTest.jqwik.syntax.parameter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Report;
import net.jqwik.api.Reporting;

public class WildCardTypes {
  @Property
  @Report(Reporting.GENERATED)
  boolean reverseWithWildcardType(@ForAll List<?> original) { //? -> 모든 타입에 대해 체크한다.
    return reverse(reverse(original)).equals(original);
  }

  private <T> List<T> reverse(List<T> original) {
    List<T> clone = new ArrayList<>(original);
    Collections.reverse(clone);
    return clone;
  }

}
