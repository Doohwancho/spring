package com.tdd.tddTest.jqwik.how_to_find_property.test_oracle.ex.java_magazine;

import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AggregatorTests {

  @Test
  void tallyOfSeveralValues() {
    Aggregator aggregator = new Aggregator();
    aggregator.receive(1);
    aggregator.receive(2);
    aggregator.receive(3);
    aggregator.receive(2);

    Map<Integer, Integer> tally = aggregator.tally();
    Assertions.assertEquals(1, (int) tally.get(1));
    Assertions.assertEquals(2, (int) tally.get(2));
    Assertions.assertEquals(1, (int) tally.get(3));
  }
}
