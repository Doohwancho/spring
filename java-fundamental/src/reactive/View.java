package reactive;

import reactive.eventListener.LoginFormEvent;
import reactive.eventListener.LoginListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private Model model;
    private LoginListener loginListener;


    private final JTextField nameField;
    private final JPasswordField passField;
    private final JButton okButton;


    public View(Model model) throws HeadlessException {
        super("MVC Demo");
        this.model = model;

        nameField = new JTextField(10);
        passField = new JPasswordField(10);
        okButton = new JButton("OK");


        //setup canvas
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.anchor = GridBagConstraints.LAST_LINE_END;
        gc.gridx = 1;
        gc.gridy = 1;
        gc.weightx=1;
        gc.weighty=1;
        gc.insets = new Insets(100,0,0,10);
        gc.fill=GridBagConstraints.NONE;

        add(new JLabel("Name: "), gc);

        gc.anchor = GridBagConstraints.LAST_LINE_START;
        gc.gridx = 2;
        gc.gridy = 1;
        gc.weightx=1;
        gc.weighty=1;
        gc.insets = new Insets(100,0,0,0);
        gc.fill=GridBagConstraints.NONE;

        add(nameField, gc);

        gc.anchor = GridBagConstraints.LINE_END;
        gc.gridx = 1;
        gc.gridy = 2;
        gc.weightx=1;
        gc.weighty=1;
        gc.insets = new Insets(0,0,0,10);
        gc.fill=GridBagConstraints.NONE;

        add(new JLabel("Password: "), gc);

        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 2;
        gc.gridy = 2;
        gc.weightx=1;
        gc.weighty=1;
        gc.insets = new Insets(0,0,0,0);
        gc.fill=GridBagConstraints.NONE;

        add(passField, gc);

        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridx = 2;
        gc.gridy = 3;
        gc.weightx=1;
        gc.weighty=100;
        gc.fill=GridBagConstraints.NONE;

        add(okButton, gc);

        okButton.addActionListener(this); //버튼에 EventListener 등록해준다.

        setSize(600,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String password = new String(passField.getPassword());
        String name = nameField.getText();

        if (loginListener != null) {
            loginListener.loginPerformed(new LoginFormEvent(name, password)); //컨트롤러에 있는 clickEventOccurred()가 실행된다.
        }
    }

    public void setLoginListener(LoginListener listener) { //controller implements ClickListener했기 때문에, 컨트롤러를 넘겨도 EventListener로 받아들임.
        this.loginListener = listener;
    }
}
