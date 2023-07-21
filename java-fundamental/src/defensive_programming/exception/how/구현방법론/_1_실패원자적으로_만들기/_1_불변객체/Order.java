package defensive_programming.exception.how.구현방법론._1_실패원자적으로_만들기._1_불변객체;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final List<OrderItem> items;
    
    public Order(List<OrderItem> items) {
        this.items = new ArrayList<>(items);
    }
    
    public List<OrderItem> getItems() {
        // 불변 리스트를 반환하여 외부에서 리스트를 변경하는 것을 방지합니다.
        return Collections.unmodifiableList(items); //이 리스트를 반환받은 코드에서 리스트를 변경하려고 하면 UnsupportedOperationException이 발생합니다.
    }
    
    // ... 다른 메서드들 ...
}
