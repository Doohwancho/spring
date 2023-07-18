package com.cho.lotto;

import com.cho.lotto.exception.NullLottoNumberInputNotAllowedException;
import com.cho.lotto.exception.MinusCashIsNotAllowedException;
import com.cho.lotto.exception.NullScratchedLottoInputException;
import com.cho.lotto.exception.WinningLottoNumberShouldBeSixDigitsException;
import com.cho.lotto.exception.ZeroScratchedLottoNowAllowedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoTest {

  private final Lotto lotto = new Lotto();

  @ValueSource(ints = {-1000, -1000000, Integer.MIN_VALUE})
  @ParameterizedTest
  void 로또_구입_금액이_음수면_안된다(int money) throws Exception {
    //when & then
    Assertions.assertThatThrownBy(() -> {
          lotto.buyLotto(money);
        }).isInstanceOf(MinusCashIsNotAllowedException.class)
        .hasMessageContaining("마이너스 돈이란 있을 수 없다!");
  }

  @ValueSource(ints = {0, 1000, 14000, 200000})
  @ParameterizedTest
  void 천원단위로_로또_발급하는지_체크(int money) throws Exception {
    //when
    List<List<Integer>> results = lotto.buyLotto(money);

    //then
    Assertions.assertThat(results.size()).isEqualTo(money / lotto.getLottoPrice());
  }

  @Test
  void 로또_번호는_6자리_체크() throws Exception {
    //given
    int money = 14000;
    boolean flag = false;

    //when
    List<List<Integer>> results = lotto.buyLotto(money);

    //then
    boolean hasInvalidsize = results.stream().anyMatch(list -> list.size() != lotto.getLottoSize());

    Assertions.assertThat(hasInvalidsize).isEqualTo(false);
  }

  @ValueSource(ints = {0, 1000, 5000, 14000, 20000})
  @ParameterizedTest
  void 로또_숫자범위_1부터_45까지_체크(int money) throws Exception {
    //when
    List<List<Integer>> results = lotto.buyLotto(money);

    //then
    boolean hasInvalidValue = results.stream().flatMap(List::stream)
        .anyMatch(value -> value < 0 || value
            > Lotto.BOUND.MAX_BOUND_OF_LOTTO.getValue()); //TODO - stream: List<List<Integer>> 스트림 어케 만들지?

    Assertions.assertThat(hasInvalidValue).isEqualTo(false);
  }

  @ValueSource(ints = {0, 1000, 5000, 14000, 20000})
  @ParameterizedTest
  void 모든_로또번호가_유일한지_체크(int money) throws Exception {
    //when
    List<List<Integer>> results = lotto.buyLotto(money);

    //then
    for (List<Integer> lotto : results) {
      Set<Integer> uniqueNumbers = new HashSet<>(lotto);

      Assertions.assertThat(uniqueNumbers.size()).isEqualTo(lotto.size());
    }
  }

  @Test
  void 로또_1등_당첨금액_체크() throws Exception {
    //given
    List<List<Integer>> 긁은복권모음 = new ArrayList<>();
    List<Integer> 당첨_6개 = Arrays.asList(1, 2, 3, 4, 5, 6);

    긁은복권모음.add(당첨_6개);

    List<Integer> 당첨번호 = Arrays.asList(1, 2, 3, 4, 5, 6);

    //when
    int result = lotto.verifyLottoResult(당첨번호, 긁은복권모음);

    //then
    Assertions.assertThat(result).isEqualTo(Lotto.PRIZE.FIRST_PRIZE.getPrizeMoney());
  }

  @Test
  void 로또_2등_당첨금액_체크() throws Exception {
    //given
    List<List<Integer>> 긁은복권모음 = new ArrayList<>();
    List<Integer> 당첨_5개 = Arrays.asList(1, 2, 3, 4, 5, 100);

    긁은복권모음.add(당첨_5개);

    List<Integer> 당첨번호 = Arrays.asList(1, 2, 3, 4, 5, 6);

    //when
    int result = lotto.verifyLottoResult(당첨번호, 긁은복권모음);

    //then
    Assertions.assertThat(result).isEqualTo(Lotto.PRIZE.SECOND_PRIZE.getPrizeMoney());
  }


  @Test
  void 로또_3등_당첨금액_체크() throws Exception {
    //given
    List<List<Integer>> 긁은복권모음 = new ArrayList<>();
    List<Integer> 당첨_4개 = Arrays.asList(1, 2, 3, 4, 100, 100);

    긁은복권모음.add(당첨_4개);

    List<Integer> 당첨번호 = Arrays.asList(1, 2, 3, 4, 5, 6);

    //when
    int result = lotto.verifyLottoResult(당첨번호, 긁은복권모음);

    //then
    Assertions.assertThat(result).isEqualTo(Lotto.PRIZE.THIRD_PRIZE.getPrizeMoney());
  }


  @Test
  void 로또_4등_당첨금액_체크() throws Exception {
    //given
    List<List<Integer>> 긁은복권모음 = new ArrayList<>();
    List<Integer> 당첨_3개 = Arrays.asList(1, 2, 3, 100, 100, 100);

    긁은복권모음.add(당첨_3개);

    List<Integer> 당첨번호 = Arrays.asList(1, 2, 3, 4, 5, 6);

    //when
    int result = lotto.verifyLottoResult(당첨번호, 긁은복권모음);

    //then
    Assertions.assertThat(result).isEqualTo(Lotto.PRIZE.FOURTH_PRIZE.getPrizeMoney());
  }

  @Test
  void 당첨되지_않은_경우_금액_체크() throws Exception {
    //given
    List<List<Integer>> 긁은복권모음 = new ArrayList<>();
    List<Integer> 꽝 = Arrays.asList(1, 2, 100, 100, 100, 100);

    긁은복권모음.add(꽝);

    List<Integer> 당첨번호 = Arrays.asList(1, 2, 3, 4, 5, 6);

    //when
    int result = lotto.verifyLottoResult(당첨번호, 긁은복권모음);

    //then
    Assertions.assertThat(result).isEqualTo(0);
  }

  @Test
  void 당첨번호가_null이면_throw_Exception() {
    //when & then
    Assertions.assertThatThrownBy(() -> {
          lotto.verifyLottoResult(null, new ArrayList<>());
        }).isInstanceOf(NullLottoNumberInputNotAllowedException.class)
        .hasMessageContaining("당첨번호가 입력되지 않았습니다.");
  }

  @Test
  void 당첨번호_갯수가_6개가아니면_throw_Exception() {
    //when & then
    Assertions.assertThatThrownBy(() -> {
          lotto.verifyLottoResult(Arrays.asList(1, 2, 3), new ArrayList<>());
        }).isInstanceOf(WinningLottoNumberShouldBeSixDigitsException.class)
        .hasMessageContaining("당첨번호 갯수는 6개여야 합니다.");
  }

  @Test
  void 긁은복권모음이_null이면_throw_Exception() {
    //when & then
    Assertions.assertThatThrownBy(() -> {
          lotto.verifyLottoResult(Arrays.asList(1, 2, 3, 4, 5, 6), null);
        }).isInstanceOf(NullScratchedLottoInputException.class)
        .hasMessageContaining("긁은복권모음이 입력되지 않았습니다.");
  }

  @Test
  void 긁은복권모음이_없으면_throw_Exception() {
    //when & then
    Assertions.assertThatThrownBy(() -> {
          lotto.verifyLottoResult(Arrays.asList(1, 2, 3, 4, 5, 6), new ArrayList<>());
        }).isInstanceOf(ZeroScratchedLottoNowAllowedException.class)
        .hasMessageContaining("긁은 복권이 없습니다.");
  }
}
