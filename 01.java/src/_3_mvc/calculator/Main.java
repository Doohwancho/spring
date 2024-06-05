package _3_mvc.calculator;

public class Main {
    public static void main(String[] args) {
        ViewCalculator view = new ViewCalculator();
        ModelCalculator model = new ModelCalculator();
        model.addObserver(view);
        model.reset();
        ControllerCalculator controller = new ControllerCalculator(model, view);
        view.setVisible(true);
    }
}
