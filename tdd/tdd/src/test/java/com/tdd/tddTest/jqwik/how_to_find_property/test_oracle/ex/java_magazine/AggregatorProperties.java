package com.tdd.tddTest.jqwik.how_to_find_property.test_oracle.ex.java_magazine;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import org.junit.jupiter.api.Assertions;

/*
  Q. 테스트 오라클이란?
  A. 테스트 실행 결과의 참/거짓 판별할 수 있는 기준


  Q. HashMap에 key 넣을 때, value를 key의 빈도수 count로 넣는걸,
     어떤 조건으로 테스트 할 수 있을까?
     test oracle 이 경우에 어케 만듬?

  A.
     1. 모든 키가 들어있는지 확인
     2. origin input을 비웠을 때, output과 키 매치가 하나도 안되야 함
     3. input 사이즈와, map에 넣은 count의 총 합과 같아야 한다.
     4. origin input의 순서가 섞여도, 안섞인 HashMap과 같아야 한다.


 */
class AggregatorProperties {

  @Property
  boolean allMeasuredValuesShowUpAsKeysInTally(@ForAll List<Integer> measurements) {
    Aggregator aggregator = new Aggregator();
    measurements.forEach(aggregator::receive);
    return measurements.stream().allMatch(m -> aggregator.tally().containsKey(m));
  }

  @Property
  boolean numbersNeverMeasuredDontShowUpInTally(
      @ForAll List<Integer> measured,
      @ForAll Set<Integer> notMeasured
  ) {
    notMeasured.removeAll(measured);

    Aggregator aggregator = new Aggregator();
    measured.forEach(aggregator::receive);
    return notMeasured.stream().noneMatch(m -> aggregator.tally().containsKey(m));
  }

  @Property
  boolean sumOfAllCountsIsNumberOfMeasurements(@ForAll List<Integer> measurements) {
    Aggregator aggregator = new Aggregator();
    measurements.forEach(aggregator::receive);
    int sumOfAllCounts = aggregator.tally().values().stream().mapToInt(i -> i).sum();
    return sumOfAllCounts == measurements.size();
  }

  @Property
  void orderOfMeasuringDoesNotChangeTally(
      @ForAll List<Integer> measurements,
      @ForAll Random random
  ) {

    Aggregator aggregator1 = new Aggregator();
    measurements.forEach(aggregator1::receive);
    Map<Integer, Integer> tally1 = aggregator1.tally();

    Collections.shuffle(measurements, random);
    Aggregator aggregator2 = new Aggregator();
    measurements.forEach(aggregator2::receive);
    Map<Integer, Integer> tally2 = aggregator2.tally();

    Assertions.assertEquals(tally1, tally2);
  }
}
