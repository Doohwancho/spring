package solid._3_liskov_substitution.example1_car;

public class ElectricCar implements Car {

    public void turnOnEngine() {
        throw new AssertionError("I don't have an engine!"); //새로 추가된 전기차는 엔진이 없다?! 그래서 에러남. 어찌하지? -> Car에 turnOnEngine() 없애자.
    }

    public void accelerate() {
        //this acceleration is crazy!
    }
}