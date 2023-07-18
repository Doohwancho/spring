package com.cho.lotto.exception;

public class NullLottoNumberInputNotAllowedException extends RuntimeException {
  public NullLottoNumberInputNotAllowedException() {
    super("당첨번호가 입력되지 않았습니다.");
  }

  public NullLottoNumberInputNotAllowedException(String message) {
    super(message);
  }
}
