package _2_oop._1_상태_데이터의_캡슐화.solid._3_liskov_substitution.example1_car;

public class MotorCar implements Car {
    private Engine engine;
    //Constructors, getters + setters

    public void turnOnEngine() {
        //turn on the engine!
        engine.on();
    }
    public void accelerate() {
        //move forward!
        engine.powerOn(1000);
    }
}