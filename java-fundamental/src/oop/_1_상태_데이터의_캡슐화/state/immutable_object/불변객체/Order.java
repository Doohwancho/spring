package oop._1_상태_데이터의_캡슐화.state.immutable_object.불변객체;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final List<OrderItem> items; //원래 가변객체여서 thread-safe하지 못한 애를, Collections.unmodifiableList(items) 로 불변객체로 만들어서 반환
    private final Object[] PRIVATE_VALUES;
    
    public Order(List<OrderItem> items, Object[] privateValues) {
        this.items = new ArrayList<>(items);
        PRIVATE_VALUES = privateValues;
    }
    
    //case1) 불변 객체로 변경해 반환
    public List<OrderItem> getItems() {
        // 불변 리스트를 반환하여 외부에서 리스트를 변경하는 것을 방지합니다.
        return Collections.unmodifiableList(items); //이 리스트를 반환받은 코드에서 리스트를 변경하려고 하면 UnsupportedOperationException이 발생합니다.
    }
    
    //case2) 방어적 복사
    public Object[] getPRIVATE_VALUES(){
        return PRIVATE_VALUES.clone(); //리턴시 값을 복사해서 줌.
    }
    
    // ... 다른 메서드들 ...
}
