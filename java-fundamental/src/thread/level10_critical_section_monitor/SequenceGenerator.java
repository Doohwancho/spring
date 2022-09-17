package thread.level10_critical_section_monitor;

public class SequenceGenerator {
	private int currentValue = 0;

    public int getNextSequence() {
        currentValue = currentValue + 1;
        return currentValue;
    }
}
