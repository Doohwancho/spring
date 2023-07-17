package design_pattern.Behavioral.observer;

public class SimpleObserver implements Observer {
	private int value;
	private Subject simpleSubject;
	
	public SimpleObserver(Subject simpleSubject) {
		this.simpleSubject = simpleSubject;
		simpleSubject.registerObserver(this);
	}
	
	public void update(int value) {
		this.value = value; //SimpleSubject 객체에서 바뀐 값 바로 스니핑 
		display();
	}
	
	public void display() {
		System.out.println("Value: " + value);
	}
}

