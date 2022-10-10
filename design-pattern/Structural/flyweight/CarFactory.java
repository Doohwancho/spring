package flyweight;

import java.util.HashMap;
import java.util.Map;

public class CarFactory {
    Map pool;

    public CarFactory() {
        pool = new HashMap(); //hashmap으로 중복제거해서 저장 
    }

    public Car getCar(String key) {
        Car car = (Car) pool.get(key);
        if (car == null) {
            car = new Car(key);
            pool.put(key, car);
            System.out.println("새로 생성 : " + key);
        } else {
            System.out.println("재사용 : " + key);
        }
        return car;
    }
}