package main.java.com.example.os.solutions.step4_monitor;

public class SequenceGenerator {
	private int currentValue = 0;

    public int getNextSequence() {
        currentValue = currentValue + 1;
        return currentValue;
    }
}
