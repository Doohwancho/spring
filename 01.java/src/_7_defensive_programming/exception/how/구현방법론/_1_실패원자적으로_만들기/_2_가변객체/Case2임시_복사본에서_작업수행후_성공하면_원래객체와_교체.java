package _7_defensive_programming.exception.how.구현방법론._1_실패원자적으로_만들기._2_가변객체;

import java.util.Optional;

public class Case2임시_복사본에서_작업수행후_성공하면_원래객체와_교체 {
    public Optional<CustomObject> doSomething(CustomObject originalObject) throws CloneNotSupportedException {
        CustomObject copy;
        try {
            copy = (CustomObject) originalObject.clone(); //이게 sort이거나, 다른 메서드일 수도 있다..
            if(!originalObject.equals(copy)) throw new CloneNotSupportedException(); //의도에 맞게 실행됬는지 확인
        } catch (CloneNotSupportedException e) { //의도에 맞게 실행 안됬으면, throw exception 후, 나머지 코드 실행 안되게끔 해서, 이 객체의 상태를 오염시키지 않는다.
            // handle the exception
            e.printStackTrace();
            return Optional.empty();
        }
        
        //validation check is done..
        //do anything..
        
        return Optional.of(copy);
    }
    
    
    class CustomObject implements Cloneable {
    
        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}
