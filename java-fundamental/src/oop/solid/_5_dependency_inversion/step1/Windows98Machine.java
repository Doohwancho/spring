package oop.solid._5_dependency_inversion.step1;

public class Windows98Machine {
    private final StandardKeyboard keyboard;
    private final Monitor monitor;

    public Windows98Machine() {
        monitor = new Monitor(); //강한 결합도! 비추
        keyboard = new StandardKeyboard();
    }
}
