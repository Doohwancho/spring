package com.cho.lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.jqwik.api.Assume;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class LottoPBT {
  private final Lotto lotto = new Lotto();

  /*
    어떻게 buyLotto()를 pbt로 테스트하지?

    1. input validation check?
      1. 마이너스 input이면 안됨
      2. Integer.MAX_VALUE 이상이면 안됨
    2. 천원 단위로 사야 함.
    3. 로또 번호가 MIN_BOUND_OF_LOTTO ~ MAX_BOUND_OF_LOTTO 이어야 함
    4. 로또 갯수가 LOTTO_SIZE 이어야 함
    5. each lotto number should be unique

    이 모든 항목을 다 pbt로 테스트 하는게 아니라, pbt가 특화된 부분만 적용하면 될 듯?
    1번은 그냥 테스트로 하면 될거같고,
    근데 2번, 3번, 4번, 5번은 pbt로 가능할..지도?
    그리고 이건 비즈니스 핵심 기능이고 좀 크리티컬 하니까, pbt가 cost heavy해도 만들어도 되지 않을까?
   */

  //2번 조건
  @Property(tries = 10) //너무 오래걸리니까 횟수 제한
  void 구입단위는_천원단위_이어야한다(@ForAll int money) throws Exception {
    Assume.that(money >= 0);
    Assertions.assertThat(lotto.buyLotto(money).size()).isEqualTo(money/1000);
  }

  //3번 조건
  @Property(tries = 10) //너무 오래걸리니까 횟수 제한
  void 로또_숫자범위_1부터_45까지_체크(@ForAll int money) throws Exception {
    //given
    Assume.that(money >= 0);

    //when
    List<List<Integer>> results = lotto.buyLotto(money);

    //then
    boolean hasInvalidValue = results.stream().flatMap(List::stream)
        .anyMatch(value -> value < 0 || value > Lotto.BOUND.MAX_BOUND_OF_LOTTO.getValue()); //TODO - stream: List<List<Integer>> 스트림 어케 만들지?

    Assertions.assertThat(hasInvalidValue).isEqualTo(false);
  }

  //4번 조건
  @Property(tries = 10) //너무 오래걸리니까 횟수 제한
  void 로또는_항상_6자리여야_한다(@ForAll int money) throws Exception {
    //given
    Assume.that(money >= 0);
    boolean flag = false;

    //when
    List<List<Integer>> results = lotto.buyLotto(money);

    //then
    boolean hasInvalidsize = results.stream().anyMatch(list -> list.size() != 6);

    Assertions.assertThat(hasInvalidsize).isEqualTo(false);
  }

  //5번 조건
  @Property(tries = 10) //너무 오래걸리니까 횟수 제한
  void 모든_로또번호가_유일한지_체크(@ForAll int money) throws Exception {
    //given
    Assume.that(money >= 0);

    //when
    List<List<Integer>> results = lotto.buyLotto(money);

    //then
    for(List<Integer> lotto : results){
      Set<Integer> uniqueNumbers = new HashSet<>(lotto);

      Assertions.assertThat(uniqueNumbers.size()).isEqualTo(lotto.size());
    }
  }

}
