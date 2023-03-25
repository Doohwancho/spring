package mvc.eventListener;

import javax.swing.*;

public class Application {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                runApp();
            }
        });
    }

    public static void runApp(){
        Model model = new Model();
        View view = new View(model);

        Controller controller = new Controller(view, model);

        view.setLoginListener(controller); //controller implements ClickListener했기 때문에, 컨트롤러를 넘겨도 EventListener로 받아들임.
    }
}
