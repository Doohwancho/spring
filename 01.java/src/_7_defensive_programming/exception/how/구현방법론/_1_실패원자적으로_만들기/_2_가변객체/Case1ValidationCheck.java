package _7_defensive_programming.exception.how.구현방법론._1_실패원자적으로_만들기._2_가변객체;

import java.util.EmptyStackException;

/*
    단점: 모든 예외케이스를 미리 생각해서 validation check에 박는다? 100%? 구조적으로 실수할 수 밖에 없음
    해결책: pbt + test coverage 써서 견고하게 테스트하자
 */
public class Case1ValidationCheck {
    
    private int size;
    private Object[] elements;
    
    public Object pop(){
        if(size == 0) throw new EmptyStackException(); //validation check로 잠재적 예외 가능성 미리 거르기
        Object result = elements[--size];
        elements[size] = null; //다 쓴 참조 해제
        return result;
    }
}
