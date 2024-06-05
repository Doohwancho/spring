package _3_mvc.prototype;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private Model model;

    public View(Model model) throws HeadlessException {
        super("MVC Demo");
        this.model = model;

        setLayout(new GridBagLayout());

        //setup canvas
        GridBagConstraints gc = new GridBagConstraints();
        gc.anchor = GridBagConstraints.CENTER;
        gc.gridheight = 0;
        gc.gridwidth = 0;
        gc.weightx=1;
        gc.weighty=1;
        gc.fill=GridBagConstraints.NONE;

        //show data
        JLabel dataLabel = new JLabel("Data: " + model.getData());
        gc.gridx = 0;
        gc.gridy = 0;
        add(dataLabel, gc);

        setSize(600,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
