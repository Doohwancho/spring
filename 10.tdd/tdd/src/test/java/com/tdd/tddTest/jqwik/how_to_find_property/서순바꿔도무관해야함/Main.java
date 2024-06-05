package com.tdd.tddTest.jqwik.how_to_find_property.서순바꿔도무관해야함;

import java.util.List;
import java.util.stream.Collectors;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.Report;
import net.jqwik.api.Reporting;
import net.jqwik.api.constraints.AlphaChars;
import org.assertj.core.api.Assertions;

public class Main {
  /*
    서순 바꿔도 동작해야 한다! (communitativity)

    If a set of functions is commutative,
    "change of order in applying the functions should not change the final result"

    ex.
    Think of sorting then filtering should have the same effect as filtering then sorting.
   */
  @Property
  @Report(Reporting.GENERATED)
  void sortingAndFilteringAreCommutative(@ForAll List<@AlphaChars String> listOfNames) {
    List<String> filteredThenSorted = listOfNames.stream()
        .filter(name -> !name.toLowerCase().contains("a")) //filter -> sort 나,
        .sorted()
        .collect(Collectors.toList());

    List<String> sortedThenFiltered = listOfNames.stream()
        .sorted()
        .filter(name -> !name.toLowerCase().contains("a")) //sort -> filter 나,
        .collect(Collectors.toList());

    Assertions.assertThat(filteredThenSorted).isEqualTo(sortedThenFiltered); //둘의 결과값이 같아야 한다.
  }

}
