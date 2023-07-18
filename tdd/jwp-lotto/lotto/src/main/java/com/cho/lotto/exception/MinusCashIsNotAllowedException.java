package com.cho.lotto.exception;

public class MinusCashIsNotAllowedException extends RuntimeException {
  public MinusCashIsNotAllowedException() {
    super("마이너스 돈이란 있을 수 없다!");
  }

  public MinusCashIsNotAllowedException(String message) {
    super(message);
  }
}
