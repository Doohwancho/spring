package com.cho.lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class Lotto {

  public static int MAX_BOUND_OF_LOTTO = 50;

  @Test 
  void 금액_입력후_천원단위_로또_발급() {
    //given
    int money = 14000;
    int amountToBuy = money / 1000;
    Random random = new Random();
    List<List<Integer>> lists = new ArrayList<>();

    //when
    for(int i = 0; i < amountToBuy; i++){
      List<Integer> list = IntStream.generate(
          () -> random.nextInt(MAX_BOUND_OF_LOTTO)).limit(6)
          .boxed().collect(Collectors.toList()); //TODO - stream: List<List<Integer>> 스트림 어케 만들지?

      lists.add(list);
    }

    //then
    //1. input이 int가 아니라 다른 형식이면 throw error 해?

    //2. input range가 마이너스가 아니고, MAX_INT보다 크면 throw error 해?

    //3. 올바룬 로또 갯수(산 금액 /1000원) 맞아?
    Assertions.assertThat(lists.size()).isEqualTo(amountToBuy);

    //4. 안에 내용물 type이 int가 맞아? 다른 타입이면 throw error 던지는거 맞아?

    //5. 안에 내용 물 6자리 다 들어있어? 7자리 이상이나, 5자리 미만이지 않아?

    //6. 랜덤한 숫자의 range가 로또 범위인 x 미만이야?

    //7. output type이 List<List<Integer>> 맞아?
  }

}
