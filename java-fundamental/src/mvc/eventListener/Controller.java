package mvc.eventListener;

import mvc.eventListener.eventListener.LoginFormEvent;
import mvc.eventListener.eventListener.LoginListener;

public class Controller implements LoginListener {
    private View view;
    private Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void loginPerformed(LoginFormEvent event) {
        System.out.println("Button clicked!");
        System.out.println("Name: " + event.getName());
        System.out.println("Password: " + event.getPassword());
    }
}
