package com.cho.lotto;

import com.cho.lotto.exception.MinusCashIsNotAllowedException;
import com.cho.lotto.exception.NullLottoNumberInputNotAllowedException;
import com.cho.lotto.exception.NullScratchedLottoInputException;
import com.cho.lotto.exception.WinningLottoNumberShouldBeSixDigitsException;
import com.cho.lotto.exception.ZeroScratchedLottoNowAllowedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Lotto {

  PRIZE prizeMoney;
  private final static int PREREQUISITES_FOR_FOURTH = 3;
  private final static int PREREQUISITES_FOR_THIRD = 4;
  private final static int PREREQUISITES_FOR_SECOND = 5;
  private final static int PREREQUISITES_FOR_FIRST = 6;
  BOUND bound;
  private static final int LOTTO_SIZE = 6;
  private static final int price = 1000;


  List<List<Integer>> buyLotto(int money) throws Exception {
    if (money < 0) {
      throw new MinusCashIsNotAllowedException();
    }

    //step1) 1천원 단위로 로또 매수가 구입 가능해야 한다.
    int amountToBuy = money / price;


    Random random = new Random();
    List<List<Integer>> lists = new ArrayList<List<Integer>>();

    for (int i = 0; i < amountToBuy; i++) {
      //step2) 1~45까지 숫자를 섞어서 랜덤하게 6자리를 뽑는다.
      List<Integer> numbers = IntStream.rangeClosed(BOUND.MIN_BOUND_OF_LOTTO.getValue(),
              BOUND.MAX_BOUND_OF_LOTTO.getValue())
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
      throw new NullLottoNumberInputNotAllowedException();
    }
    if (당첨번호.size() != 6) {
      throw new WinningLottoNumberShouldBeSixDigitsException();
    }
    if (긁은복권모음 == null) {
      throw new NullScratchedLottoInputException();
    }
    if (긁은복권모음.size() == 0) {
      throw new ZeroScratchedLottoNowAllowedException();
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
        case PREREQUISITES_FOR_FOURTH:
          당첨통계.put(PRIZE.FOURTH_PRIZE, 1);
          break;
        case PREREQUISITES_FOR_THIRD:
          당첨통계.put(PRIZE.THIRD_PRIZE, 1);
          break;
        case PREREQUISITES_FOR_SECOND:
          당첨통계.put(PRIZE.SECOND_PRIZE, 1);
          break;
        case PREREQUISITES_FOR_FIRST:
          당첨통계.put(PRIZE.FIRST_PRIZE, 1);
          break;
      }
    }
    //step3) map 돌면서 당첨통계 print
    System.out.println("당첨 통계");
    System.out.println("-----------");
    당첨통계.entrySet().stream()
        .sorted((a, b) -> a.getKey().getPrizeMoney() - b.getKey().getPrizeMoney()).forEach(
            x -> System.out.println(
                x.getKey().getPrerequisites() + "개 일치 (" + x.getKey().getPrizeName() + ")- " + x.getValue() + "개")); //TODO - ?개를 어떻겍 엘레강스하게 표현하지?
    System.out.println();

    //step4) map 돌면서 당첨금액 합산
    당첨금액 = 당첨통계.entrySet().stream().mapToInt(e -> e.getKey().getPrizeMoney() * e.getValue()).sum();

    return 당첨금액;
  }

  enum PRIZE {
    FIRST_PRIZE(2_000_000_000, PREREQUISITES_FOR_FIRST),
    SECOND_PRIZE(1_500_000, PREREQUISITES_FOR_SECOND),
    THIRD_PRIZE(50_000, PREREQUISITES_FOR_THIRD),
    FOURTH_PRIZE(5_000, PREREQUISITES_FOR_FOURTH);


    private final int prizeMoney;
    private final int prerequisites;


    PRIZE(int prizeMoney, int prerequisites) {
      this.prizeMoney = prizeMoney;
      this.prerequisites = prerequisites;
    }

    String getPrizeName() {
      return name();
    }

    int getPrizeMoney() {
      return prizeMoney;
    }

    int getPrerequisites() {
      return prerequisites;
    }
  }

  enum BOUND {
    MIN_BOUND_OF_LOTTO(1),
    MAX_BOUND_OF_LOTTO(45);

    private final int bound;


    BOUND(int bound) {
      this.bound = bound;
    }

    String getNameOfBound() {
      return name();
    }

    int getValue() {
      return bound;
    }
  }

  int getLottoSize() {
    return LOTTO_SIZE;
  }

  int getLottoPrice() {
    return price;
  }
}