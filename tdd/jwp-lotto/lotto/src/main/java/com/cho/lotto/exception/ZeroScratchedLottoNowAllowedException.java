package com.cho.lotto.exception;

public class ZeroScratchedLottoNowAllowedException extends RuntimeException {
  public ZeroScratchedLottoNowAllowedException(){
    super("긁은 복권이 없습니다.");
  }
  public ZeroScratchedLottoNowAllowedException(String msg){
    super(msg);
  }
}
