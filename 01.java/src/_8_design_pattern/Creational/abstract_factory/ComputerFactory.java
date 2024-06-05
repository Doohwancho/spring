package _8_design_pattern.Creational.abstract_factory;

public class ComputerFactory {
    public void createComputer(String type){
        KeyboardFactory keyboardFactory = new KeyboardFactory();
        MouseFactory mouseFactory = new MouseFactory();

        keyboardFactory.createKeyboard(type); //역시 조립은 Keyboard, Mouse Factory에서 한다.
        mouseFactory.createMouse(type);
        System.out.println("--- " + type + " 컴퓨터 완성 ---");
    }
}