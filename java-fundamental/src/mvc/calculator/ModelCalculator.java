package mvc.calculator;

import java.util.ArrayList;

public class ModelCalculator {
    private int data;
    private ArrayList<MyObserver> list = new ArrayList<MyObserver>();

    public void addObserver(MyObserver o) {
        list.add(o);
    }

    public void add(int num1, int num2) {
        data = num1 + num2;
        for (MyObserver o : list) {
            o.updateResult(data);
        }
    }

    public void setNum_1(int i) {
        for (MyObserver o : list) {
            o.setNum_1(i);
        }
    }

    public void setNum_2(int i) {
        for (MyObserver o : list) {
            o.setNum_2(i);
        }
    }

    public void reset() {
        for (MyObserver o : list) {
            o.reset();
        }
    }
}
