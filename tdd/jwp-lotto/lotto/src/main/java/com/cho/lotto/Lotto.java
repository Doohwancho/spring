package com.cho.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Lotto {

  enum PRIZE {
    FIRST_PRIZE(2_000_000_000),
    SECOND_PRIZE(1_500_000),
    THIRD_PRIZE(50_000),
    FOURTH_PRIZE(5_000);

    private final int prizeMoney;

    PRIZE(int prizeMoney) {
      this.prizeMoney = prizeMoney;
    }

    String getKey() {
      return name();
    }

    int getValue(){
      return prizeMoney;
    }
  }

  PRIZE prizeMoney;

  enum BOUND {
    MIN_BOUND_OF_LOTTO(1),
    MAX_BOUND_OF_LOTTO(45);

    private final int bound;

    BOUND(int bound) {
      this.bound = bound;
    }

    String getKey(){
      return name();
    }

    int getValue(){
      return bound;
    }
  }
  BOUND bound;
  private static final int LOTTO_SIZE = 6;

  int getLottoSize(){
    return LOTTO_SIZE;
  }

  public Lotto() {
  }

  List<List<Integer>> buyLotto(int money) throws Exception {
    if (money < 0) {
      throw new Exception("마이너스 돈이란 있을 수 없다!");
    }
    if (money > Integer.MAX_VALUE) {
      throw new Exception("너 미쳤니?");
    }
    int amountToBuy = money / 1000;
    Random random = new Random();
    List<List<Integer>> lists = new ArrayList<List<Integer>>();

    for (int i = 0; i < amountToBuy; i++) {
      List<Integer> numbers = IntStream.rangeClosed(BOUND.MIN_BOUND_OF_LOTTO.getValue(), BOUND.MAX_BOUND_OF_LOTTO.getValue())
          .boxed().collect(
              Collectors.toList()); //1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45

      Collections.shuffle(numbers, random);  // Shuffle the numbers
      List<Integer> lottoNumbers = numbers.stream().limit(LOTTO_SIZE).collect(Collectors.toList());

      lists.add(lottoNumbers);
    }
    return lists;
  }

  int verifyLottoResult(List<Integer> 당첨번호, List<List<Integer>> 긁은복권모음) throws Exception {
    if (당첨번호 == null) {
      throw new Exception("당첨번호가 입력되지 않았습니다.");
    }
    if (당첨번호.size() != 6) {
      throw new Exception("당첨번호 갯수는 6개여야 합니다.");
    }
    if (긁은복권모음 == null) {
      throw new Exception("긁은복권모음이 입력되지 않았습니다.");
    }
    if (긁은복권모음.size() == 0) {
      throw new Exception("긁은 복권이 없습니다.");
    }

    int 당첨금액 = 0;

    Map<PRIZE, Integer> 당첨통계 = new HashMap<>();
    당첨통계.put(PRIZE.FOURTH_PRIZE, 0);
    당첨통계.put(PRIZE.THIRD_PRIZE, 0);
    당첨통계.put(PRIZE.SECOND_PRIZE, 0);
    당첨통계.put(PRIZE.FIRST_PRIZE, 0);

    for (List<Integer> 긁은복권 : 긁은복권모음) {
      //step1) 긁은복권에서 당첨번호가 몇개 걸리는지 stream으로 확인
      int 당첨갯수 = (int) 긁은복권.stream().filter(당첨번호::contains).count();

      //step2) 3,4,5,6 인 경우, switch로 당첨통계에 넣기
      switch (당첨갯수) {
        case 3:
          당첨통계.put(PRIZE.FOURTH_PRIZE, 1);
          break;
        case 4:
          당첨통계.put(PRIZE.THIRD_PRIZE, 1);
          break;
        case 5:
          당첨통계.put(PRIZE.SECOND_PRIZE, 1);
          break;
        case 6:
          당첨통계.put(PRIZE.FIRST_PRIZE, 1);
          break;
      }
    }
    //step3) map 돌면서 당첨통계 print
    System.out.println("당첨 통계");
    System.out.println("-----------");
    당첨통계.entrySet().stream().sorted((a, b) -> a.getKey().getValue() - b.getKey().getValue()).forEach(
        x -> System.out.println(
            "?개 일치 (" + x.getKey() + ")- " + x.getValue() + "개")); //TODO - ?개를 어떻겍 엘레강스하게 표현하지?
    System.out.println();

    //step4) map 돌면서 당첨금액 합산
    당첨금액 = 당첨통계.entrySet().stream().mapToInt(e -> e.getKey().getValue() * e.getValue()).sum();

    return 당첨금액;
  }
}