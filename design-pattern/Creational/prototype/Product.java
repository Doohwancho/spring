package prototype;

import java.util.HashMap;
import java.util.Map;

public interface Product extends Cloneable { //자바에서 객체 복사하려면 Cloneable 상속받아야 한다. 
    Map<String, String> maps = new HashMap<>();

    String use(String use);

    Product createClone();
}
