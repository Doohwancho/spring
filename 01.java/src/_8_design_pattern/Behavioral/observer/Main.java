package _8_design_pattern.Behavioral.observer;

public class Main {
	public static void main(String[] args) {
		SimpleSubject simpleSubject = new SimpleSubject();
	
		SimpleObserver simpleObserver = new SimpleObserver(simpleSubject);

		simpleSubject.setValue(80); //객체 상태가 바뀌면, 바로 observer에게 상태 바뀌었다고 알려준다. 
		
		simpleSubject.removeObserver(simpleObserver);
	}
}
