package _8_design_pattern.Behavioral.state.whenToUse;

//if-else hell due to different states

public class GumballMachine {
	private static final int SOLD_OUT = 0;
	private static final int NO_QUARTER = 1;
	private static final int HAS_QUARTER = 2;
	private static final int SOLD = 3;
	
	private int state = SOLD_OUT;
	private int count = 0;
	
	public void insertQuarter() {
		if(state == HAS_QUARTER) {
			
		} else if(state == NO_QUARTER) {
			
		} else if(state == SOLD_OUT) {
			
		} else if(state == SOLD) {
			
		}
	}
	
	public void ejectQuarter() {
		if(state == HAS_QUARTER) {
			
		} else if(state == NO_QUARTER) {
			
		} else if(state == SOLD_OUT) {
			
		} else if(state == SOLD) {
			
		}
	}
	
	public void turnCrank() { //레버 돌리기
		if(state == HAS_QUARTER) {
			
		} else if(state == NO_QUARTER) {
			
		} else if(state == SOLD_OUT) {
			
		} else if(state == SOLD) {
			
		}
	}
	
	public void dispense() { //알맹이 꺼내기
		if(state == HAS_QUARTER) {
			
		} else if(state == NO_QUARTER) {
			
		} else if(state == SOLD_OUT) {
			
		} else if(state == SOLD) {
			
		}
	}
}