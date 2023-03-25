package mvc.calculator;

public interface MyObserver {
    public void updateResult(int data);
    public void reset();
    public void setNum_1(int i);
    public void setNum_2(int i);
}
