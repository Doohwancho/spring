package com.cho.lotto.exception;

public class NullScratchedLottoInputException extends RuntimeException{
  public NullScratchedLottoInputException() {
    super("긁은복권모음이 입력되지 않았습니다.");
  }

  public NullScratchedLottoInputException(String message) {
    super(message);
  }
}
