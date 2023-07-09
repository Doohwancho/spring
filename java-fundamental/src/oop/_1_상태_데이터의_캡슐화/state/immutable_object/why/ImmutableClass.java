package oop._1_상태_데이터의_캡슐화.state.immutable_object.why;

/*
  Q. why use 불변 객체?

  A. 멀티 쓰레드 환경에서, 여러 객체들에게 안전하게, thread-safe 하게 공유할 수 있어서.

 */
public class ImmutableClass {

  private final int value1; //불변 객체 안 불변 피드
  private final int value2;

  private ImmutableClass(int value1, int value2){ //private 생성자라 new ImmutableClass() 못한다.
    this.value1 = value1;
    this.value2 = value2;
  }

  public static ImmutableClass getInstance(int value1, int value2){
    return new ImmutableClass(value1, value2);
  }

}
