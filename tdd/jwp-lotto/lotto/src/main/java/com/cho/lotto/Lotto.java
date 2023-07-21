package com.cho.lotto;

import com.cho.lotto.exception.MinusCashIsNotAllowedException;
import com.cho.lotto.exception.NullLottoNumberInputNotAllowedException;
import com.cho.lotto.exception.NullScratchedLottoInputException;
import com.cho.lotto.exception.WinningLottoNumberShouldBeSixDigitsException;
import com.cho.lotto.exception.ZeroScratchedLottoNowAllowedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Lotto {
    
    private final Logger logger = Logger.getLogger(Lotto.class.getName());
    PRIZE prizeMoney;
    private static final int PREREQUISITES_FOR_FOURTH = 3;
    private static final int PREREQUISITES_FOR_THIRD = 4;
    private static final int PREREQUISITES_FOR_SECOND = 5;
    private static final int PREREQUISITES_FOR_FIRST = 6;
    BOUND bound;
    private static final int LOTTO_SIZE = 6;
    private static final int LOTTO_PRICE = 1000;
    private Random random;
    
    
    Lotto() {
        this.random = new Random();
    }
    
    List<List<Integer>> buyLotto(int money) {
        if (money < 0) {
            throw new MinusCashIsNotAllowedException();
        }
        
        //step1) 1천원 단위로 로또 매수가 구입 가능해야 한다.
        final int amountToBuy = money / LOTTO_PRICE;
        
        final List<List<Integer>> lists = new ArrayList<>();
        
        for (int i = 0; i < amountToBuy; i++) {
            //step2) 1~45까지 숫자를 섞어서 랜덤하게 6자리를 뽑는다.
            final List<Integer> numbers = IntStream.rangeClosed(
                    BOUND.MIN_BOUND_OF_LOTTO.getBoundary(), BOUND.MAX_BOUND_OF_LOTTO.getBoundary())
                .boxed().collect(
                    Collectors.toList()); //1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45
            
            Collections.shuffle(numbers, random);  // Shuffle the numbers
            final List<Integer> lottoNumbers = numbers.stream().limit(LOTTO_SIZE)
                .collect(Collectors.toList());
            
            lists.add(lottoNumbers);
        }
        return lists;
    }
    
    int verifyLottoResult(List<Integer> 당첨번호, List<List<Integer>> 긁은복권모음) {
        if (당첨번호 == null) {
            throw new NullLottoNumberInputNotAllowedException();
        }
        if (당첨번호.size() != LOTTO_SIZE) {
            throw new WinningLottoNumberShouldBeSixDigitsException();
        }
        if (긁은복권모음 == null) {
            throw new NullScratchedLottoInputException();
        }
        if (긁은복권모음.isEmpty()) {
            throw new ZeroScratchedLottoNowAllowedException();
        }
        
        int 당첨금액 = 0;
        
        EnumMap<PRIZE, Integer> 당첨통계 = new EnumMap<>(PRIZE.class);
        당첨통계.put(PRIZE.FOURTH_PRIZE, 0);
        당첨통계.put(PRIZE.THIRD_PRIZE, 0);
        당첨통계.put(PRIZE.SECOND_PRIZE, 0);
        당첨통계.put(PRIZE.FIRST_PRIZE, 0);
        
        for (final List<Integer> 긁은복권 : 긁은복권모음) {
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
                default:
                    break;
            }
        }
        //step3) map 돌면서 당첨통계 print
        logger.info("당첨 통계");
        logger.info("-----------");
        당첨통계.entrySet().stream()
            .sorted((a, b) -> a.getKey().getPrizeMoney() - b.getKey().getPrizeMoney()).forEach(
                x -> logger.info(
                    x.getKey().getMatchCount() + "개 일치 (" + x.getKey().getPrizeName() + ")- "
                        + x.getValue() + "개")); //TODO - ?개를 어떻겍 엘레강스하게 표현하지?
        logger.info("");
        
        //step4) map 돌면서 당첨금액 합산
        당첨금액 = 당첨통계.entrySet().stream().mapToInt(e -> e.getKey().getPrizeMoney() * e.getValue())
            .sum();
        
        return 당첨금액;
    }
    
    enum PRIZE {
        FIRST_PRIZE(2_000_000_000, PREREQUISITES_FOR_FIRST), SECOND_PRIZE(1_500_000,
            PREREQUISITES_FOR_SECOND), THIRD_PRIZE(50_000, PREREQUISITES_FOR_THIRD), FOURTH_PRIZE(
            5_000, PREREQUISITES_FOR_FOURTH);
        
        
        private final int prizeMoney;
        private final int matchCount;
        
        PRIZE(final int prizeMoney, final int matchCount) {
            this.prizeMoney = prizeMoney;
            this.matchCount = matchCount;
        }
        
        String getPrizeName() {
            return this.name();
        }
        
        int getPrizeMoney() {
            return this.prizeMoney;
        }
        
        int getMatchCount() {
            return this.matchCount;
        }
    }
    
    enum BOUND {
        MIN_BOUND_OF_LOTTO(1), MAX_BOUND_OF_LOTTO(45);
        
        private final int boundary;
        
        BOUND(final int boundary) {
            this.boundary = boundary;
        }
        
        int getBoundary() {
            return this.boundary;
        }
    }
    
    int getLottoSize() {
        return Lotto.LOTTO_SIZE;
    }
    
    int getLottoPrice() {
        return Lotto.LOTTO_PRICE;
    }
}