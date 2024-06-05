package com.tdd.tddTest.jqwik.optimization;

import net.jqwik.api.Arbitraries;

public class MapAndFilter {

  public static void main(String[] args) {
    //Q. both works the same, but which way is better?

    //case1
    Arbitraries.integers().between(1, 300).filter(anInt -> anInt % 6 == 0);

    //case2)
    Arbitraries.integers().between(1, 50).map(anInt -> anInt * 6);


    //A. case1은 readability 더 좋긴 하나, 250개 날려야 함
    //   반면, case2는 readability 약간 보기하나, 성능 더 좋음

  }

}
