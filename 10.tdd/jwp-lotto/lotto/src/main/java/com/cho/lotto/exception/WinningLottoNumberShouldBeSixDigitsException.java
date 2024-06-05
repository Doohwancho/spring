package com.cho.lotto.exception;

public class WinningLottoNumberShouldBeSixDigitsException extends RuntimeException {
  public WinningLottoNumberShouldBeSixDigitsException() {
    super("당첨번호 갯수는 6개여야 합니다.");
  }

  public WinningLottoNumberShouldBeSixDigitsException(String message) {
    super(message);
  }
}
