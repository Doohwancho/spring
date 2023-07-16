package com.cho.lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class Lotto {

  public static final int FOURTH_PRIZE = 5000;
  public static final int THIRD_PRIZE = 50000;
  public static final int SECOND_PRIZE = 1500000;
  public static final int FIRST_PRIZE = 2000000000;
  public static final int MIN_BOUND_OF_LOTTO = 1;
  public static final int MAX_BOUND_OF_LOTTO = 45;
  public static final int LOTTO_SIZE = 6;

  List<List<Integer>> buyLotto(int money) throws Exception {
    if(money < 0){
      throw new Exception("마이너스 돈이란 있을 수 없다!");
    }
    if(money > Integer.MAX_VALUE){
      throw new Exception("너 미쳤니?");
    }
    int amountToBuy = money / 1000;
    Random random = new Random();
    List<List<Integer>> lists = new ArrayList<>();

    for(int i = 0; i < amountToBuy; i++){
      List<Integer> numbers = IntStream.rangeClosed(MIN_BOUND_OF_LOTTO, MAX_BOUND_OF_LOTTO)
          .boxed().collect(Collectors.toList()); //1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45

      Collections.shuffle(numbers, random);  // Shuffle the numbers
      List<Integer> lottoNumbers = numbers.stream().limit(LOTTO_SIZE).collect(Collectors.toList());

      lists.add(lottoNumbers);
    }
    return lists;
  }

  int verifyLottoResult(List<Integer> 당첨번호, List<List<Integer>> 긁은복권모음) throws Exception {
    if(당첨번호 == null){
      throw new Exception("당첨번호가 입력되지 않았습니다.");
    }
    if(당첨번호.size() != 6){
      throw new Exception("당첨번호 갯수는 6개여야 합니다.");
    }
    if(긁은복권모음 == null){
      throw new Exception("긁은복권모음이 입력되지 않았습니다.");
    }
    if(긁은복권모음.size() == 0){
      throw new Exception("긁은 복권이 없습니다.");
    }

    int 당첨금액 = 0;

    Map<Integer, Integer> 당첨통계 = new HashMap<>();
    당첨통계.put(FOURTH_PRIZE, 0);
    당첨통계.put(THIRD_PRIZE, 0);
    당첨통계.put(SECOND_PRIZE, 0);
    당첨통계.put(FIRST_PRIZE, 0);

    for(List<Integer> 긁은복권 : 긁은복권모음){
      //step1) 긁은복권에서 당첨번호가 몇개 걸리는지 stream으로 확인
      int 당첨갯수 = (int)긁은복권.stream().filter(당첨번호::contains).count();

      //step2) 3,4,5,6 인 경우, switch로 당첨통계에 넣기
      switch(당첨갯수){
        case 3:
          당첨통계.put(FOURTH_PRIZE, 1);
          break;
        case 4:
          당첨통계.put(THIRD_PRIZE, 1);
          break;
        case 5:
          당첨통계.put(SECOND_PRIZE, 1);
          break;
        case 6:
          당첨통계.put(FIRST_PRIZE, 1);
          break;
      }
    }
    //step3) map 돌면서 당첨통계 print
    System.out.println("당첨 통계");
    System.out.println("-----------");
    당첨통계.entrySet().stream().sorted((a, b) -> a.getKey() - b.getKey()).forEach(x -> System.out.println("?개 일치 ("+x.getKey()+")- "+x.getValue()+"개")); //TODO - ?개를 어떻겍 엘레강스하게 표현하지?
    System.out.println();

    //step4) map 돌면서 당첨금액 합산
    당첨금액 = 당첨통계.entrySet().stream().mapToInt(e -> e.getKey() * e.getValue()).sum();

    return 당첨금액;
  }



  @ValueSource(ints = {-1000, -1000000, Integer.MIN_VALUE})
  @ParameterizedTest
  void 로또_구입_금액이_음수면_안된다(int money) throws Exception {
    //given

    //when & then
    Assertions.assertThatThrownBy(() -> {
      buyLotto(money);
    }).isInstanceOf(Exception.class)
    .hasMessageContaining("마이너스 돈이란 있을 수 없다");
  }

//  @Disabled("이거 어떻게 테스트하지?")
//  @ParameterizedTest
//  void 로또_구입_최대금액_제한(int money) throws Exception {
//    //given
//    String bigNumber = "2147483648"; // Integer.MAX_VALUE is 2147483647
//
//    //when & then
//    Assertions.assertThatThrownBy(() -> {
//      int input = Integer.parseInt(bigNumber);
//      buyLotto(input);
//    }).isInstanceOf(Exception.class)
//    .hasMessageContaining("너 미쳤니?");
//  }

  @ValueSource(ints = {0, 1000, 14000, 200000})
  @ParameterizedTest
  void 천원단위로_로또_발급하는지_체크(int money) throws Exception {
    //when
    List<List<Integer>> results = buyLotto(money);

    //then
    Assertions.assertThat(results.size()).isEqualTo(money / 1000);
  }
  
  @Test 
  void 로또_번호는_6자리_체크() throws Exception {
    //given
    int money = 14000;
    boolean flag = false;

    //when
    List<List<Integer>> results = buyLotto(money);

    //then
    boolean hasInvalidsize = results.stream().anyMatch(list -> list.size() != 6);

    Assertions.assertThat(hasInvalidsize).isEqualTo(false);
  }

  @ValueSource(ints = {0, 1000, 5000, 14000, 20000})
  @ParameterizedTest
  void 로또_숫자범위_1부터_50까지_체크(int money) throws Exception {
    //when
    List<List<Integer>> results = buyLotto(money);

    //then
    boolean hasInvalidValue = results.stream().flatMap(List::stream)
        .anyMatch(value -> value < 0 || value > MAX_BOUND_OF_LOTTO); //TODO - stream: List<List<Integer>> 스트림 어케 만들지?

    Assertions.assertThat(hasInvalidValue).isEqualTo(false);
 }

  @ValueSource(ints = {0, 1000, 5000, 14000, 20000})
  @ParameterizedTest
  void 모든_로또번호가_유일한지_체크(int money) throws Exception {
    //when
    List<List<Integer>> results = buyLotto(money);

    //then
    for(List<Integer> lotto : results){
      Set<Integer> uniqueNumbers = new HashSet<>(lotto);

      Assertions.assertThat(uniqueNumbers.size()).isEqualTo(lotto.size());
    }
  }

  @Test
  void 로또_1등_당첨금액_체크() throws Exception {
    //given
    List<List<Integer>> 긁은복권모음 = new ArrayList<>();
    List<Integer> 당첨_6개 = Arrays.asList(1,2,3,4,5,6);

    긁은복권모음.add(당첨_6개);

    List<Integer> 당첨번호 = Arrays.asList(1,2,3,4,5,6);

    //when
    int result = verifyLottoResult(당첨번호, 긁은복권모음);

    //then
    Assertions.assertThat(result).isEqualTo(FIRST_PRIZE);
  }

  @Test
  void 로또_2등_당첨금액_체크() throws Exception {
      //given
      List<List<Integer>> 긁은복권모음 = new ArrayList<>();
      List<Integer> 당첨_5개 = Arrays.asList(1,2,3,4,5,100);

      긁은복권모음.add(당첨_5개);

      List<Integer> 당첨번호 = Arrays.asList(1,2,3,4,5,6);

      //when
      int result = verifyLottoResult(당첨번호, 긁은복권모음);

      //then
      Assertions.assertThat(result).isEqualTo(SECOND_PRIZE);
  }


  @Test
  void 로또_3등_당첨금액_체크() throws Exception {
    //given
    List<List<Integer>> 긁은복권모음 = new ArrayList<>();
    List<Integer> 당첨_4개 = Arrays.asList(1,2,3,4,100,100);

    긁은복권모음.add(당첨_4개);

    List<Integer> 당첨번호 = Arrays.asList(1,2,3,4,5,6);

    //when
    int result = verifyLottoResult(당첨번호, 긁은복권모음);

    //then
    Assertions.assertThat(result).isEqualTo(THIRD_PRIZE);
  }



  @Test
  void 로또_4등_당첨금액_체크() throws Exception {
    //given
    List<List<Integer>> 긁은복권모음 = new ArrayList<>();
    List<Integer> 당첨_3개 = Arrays.asList(1,2,3,100,100,100);

    긁은복권모음.add(당첨_3개);

    List<Integer> 당첨번호 = Arrays.asList(1,2,3,4,5,6);

    //when
    int result = verifyLottoResult(당첨번호, 긁은복권모음);

    //then
    Assertions.assertThat(result).isEqualTo(FOURTH_PRIZE);
  }

  @Test
  void 당첨되지_않은_경우_금액_체크() throws Exception {
    //given
    List<List<Integer>> 긁은복권모음 = new ArrayList<>();
    List<Integer> 꽝 = Arrays.asList(1,2,100,100,100,100);

    긁은복권모음.add(꽝);

    List<Integer> 당첨번호 = Arrays.asList(1,2,3,4,5,6);

    //when
    int result = verifyLottoResult(당첨번호, 긁은복권모음);

    //then
    Assertions.assertThat(result).isEqualTo(0);
  }

  @Test
  void 당첨번호가_null이면_throw_Exception() {
      //when & then
      Assertions.assertThatThrownBy(() -> {
        verifyLottoResult(null, new ArrayList<>());
      }).isInstanceOf(Exception.class)
      .hasMessageContaining("당첨번호가 입력되지 않았습니다.");
  }
  @Test
  void 당첨번호_갯수가_6개가아니면_throw_Exception() {
      //when & then
      Assertions.assertThatThrownBy(() -> {
        verifyLottoResult(Arrays.asList(1,2,3), new ArrayList<>());
      }).isInstanceOf(Exception.class)
      .hasMessageContaining("당첨번호 갯수는 6개여야 합니다.");
  }

  @Test
  void 긁은복권모음이_null이면_throw_Exception() {
      //when & then
      Assertions.assertThatThrownBy(() -> {
        verifyLottoResult(Arrays.asList(1,2,3,4,5,6), null);
      }).isInstanceOf(Exception.class)
      .hasMessageContaining("긁은복권모음이 입력되지 않았습니다");
  }
  @Test
  void 긁은복권모음이_없으면_throw_Exception() {
      //when & then
      Assertions.assertThatThrownBy(() -> {
        verifyLottoResult(Arrays.asList(1,2,3,4,5,6), new ArrayList<>());
      }).isInstanceOf(Exception.class)
      .hasMessageContaining("긁은 복권이 없습니다.");
  }
}
