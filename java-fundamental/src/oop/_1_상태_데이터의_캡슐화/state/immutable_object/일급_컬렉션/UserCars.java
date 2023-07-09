package oop._1_상태_데이터의_캡슐화.state.immutable_object.일급_컬렉션;

import java.util.List;

/*
  Q. what is 일급 컬렉션?

  A. Collection(ex. list, tree, map, etc)을 래핑한 클래스

  규칙: Collection 이외에 다른 변수가 없어야 한다.

 */
public class UserCars { //pros: Collection에 이름에 의미 부여할 수 있다!
  private final List<Car> cars; //feat: final이라 불변 객체. private이라 직접접근 불가.

  private UserCars(List<Car> cars) {
    this.cars = cars;
  }

  public static UserCars getInstance(List<Car> cars) throws Exception {
    if(cars.size() > 10) { //pros: 조건도 걸 수 있다.
      throw new Exception("사이즈가 너무 커!");
    }
    if(cars.contains(new Car("Benz"))){ //pros: validation check도 걸 수 있다.
      throw new Exception();
    }
    return new UserCars(cars);
  }

  public Car getMainCar() throws Exception {
    return cars.stream().findFirst().orElseThrow(Exception::new);
  }
}
