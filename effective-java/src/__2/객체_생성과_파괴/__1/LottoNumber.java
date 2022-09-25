package __2.객체_생성과_파괴.__1;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class LottoNumber {
	  private static final int MIN_LOTTO_NUMBER = 1;
	  private static final int MAX_LOTTO_NUMBER = 45;

	  private static Map<Integer, LottoNumber> lottoNumberCache = new HashMap<>();

	  static {
	    IntStream.range(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER)
	                .forEach(i -> lottoNumberCache.put(i, new LottoNumber(i)));
	  }

	  private int number;

	  private LottoNumber(int number) {
	    this.number = number;
	  }

	  public static LottoNumber of(int number) {  // LottoNumber를 반환하는 정적 팩토리 메서드
	    return lottoNumberCache.get(number);
	  }
}