package com.cho.lotto;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class Lotto {

  public static int MAX_BOUND_OF_LOTTO = 45;
  public static int LOTTO_SIZE = 6;


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
      List<Integer> list = IntStream.generate(
              () -> random.nextInt(MAX_BOUND_OF_LOTTO)).limit(LOTTO_SIZE)
          .boxed().collect(Collectors.toList()); //TODO - stream: List<List<Integer>> 스트림 어케 만들지?

      lists.add(list);
    }
    return lists;
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
        .anyMatch(value -> value < 0 || value > MAX_BOUND_OF_LOTTO);

    Assertions.assertThat(hasInvalidValue).isEqualTo(false);
 }
}
