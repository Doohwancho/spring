package oop._1_상태_데이터의_캡슐화.nested_class.멤버_클래스.car;
public class Car {
    private Engine engine;
    private Wheel wheel;
    
    public Car() {
        this.engine = new Engine();
        this.wheel = new Wheel();
    }
    
    public void start() {
        this.engine.start();
    }
    
    public void turnWheel() {
        this.wheel.turn();
    }
    
    public class Engine {
        public void start() {
            System.out.println("Engine started");
        }
    }
    
    public class Wheel {
        public void turn() {
            System.out.println("Wheel turned");
        }
    }
}
