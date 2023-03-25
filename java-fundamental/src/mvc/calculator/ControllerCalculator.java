package mvc.calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerCalculator implements ActionListener {
    private ModelCalculator model;
    private ViewCalculator view;

    public ControllerCalculator(ModelCalculator model, ViewCalculator view) {
        this.model = model;
        this.view = view;
        this.view.setCalculatorListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.btnCalc) {
            int num1 = view.getNum1();
            int num2 = view.getNum2();
            model.add(num1, num2);
        } else if (e.getSource() == view.btnReset) {
            model.reset();
        } else {
            for (int i = 0; i < 10; i++) {
                if (e.getSource() == view.btns[i]) {
                    if(!view.getIsNum1()){
                        model.setNum_1(i);
                    } else {
                        model.setNum_2(i);
                    }
                }
            }
        }
    }
}
