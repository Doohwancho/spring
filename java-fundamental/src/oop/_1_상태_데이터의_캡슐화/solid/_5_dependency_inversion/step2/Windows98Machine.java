package oop._1_상태_데이터의_캡슐화.solid._5_dependency_inversion.step2;

public class Windows98Machine {
    private final Keyboard keyboard; //step1처럼 StandardKeyboard쓰지 않고, 인터페이스로 Keyboard써서, 여러 키보드 종류가 호환되도록 함.
    private final Monitor monitor;

    public Windows98Machine(Keyboard keyboard, Monitor monitor) {
        this.keyboard = keyboard;
        this.monitor = monitor;
    }
}
