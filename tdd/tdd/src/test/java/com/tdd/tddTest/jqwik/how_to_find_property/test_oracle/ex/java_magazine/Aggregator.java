package com.tdd.tddTest.jqwik.how_to_find_property.test_oracle.ex.java_magazine;

import java.util.HashMap;
import java.util.Map;

public class Aggregator {

  private final HashMap<Integer, Integer> tally = new HashMap<>();

  public void receive(int measurement) {
    int previousCount = tally.getOrDefault(measurement, 0);
//    tally.put(measurement, 1); // a bug
     tally.put(measurement, previousCount + 1); // not a bug ;-)
  }

  public Map<Integer, Integer> tally() {
    return tally;
  }
}
