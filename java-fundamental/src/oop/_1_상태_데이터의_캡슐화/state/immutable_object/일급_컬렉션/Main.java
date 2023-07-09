package oop._1_상태_데이터의_캡슐화.state.immutable_object.일급_컬렉션;

import java.util.ArrayList;
import java.util.List;
public class Main {
  public static void main(String[] args) throws Exception {
    List<Car> list = new ArrayList<>();
    Car car1 = new Car("Hyundai");
    Car car2 = new Car("Honda");
    list.add(car1);
    list.add(car2);

    UserCars userCars = UserCars.getInstance(list);
//    userCars.add(new Car()); // 불변을 보장한다.
  }
}
