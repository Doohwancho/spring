package observer;

import java.util.ArrayList;
import java.util.List;

public class SimpleSubject implements Subject {
	private List<Observer> observers;
	private int value = 0;
	
	public SimpleSubject() {
		observers = new ArrayList<Observer>();
	}
	
	public void registerObserver(Observer o) {
		observers.add(o);
	}
	
	public void removeObserver(Observer o) {
		observers.remove(o);
	}
	
	public void notifyObservers() {
		for (Observer observer : observers) {
			observer.update(value); //바로 담당 일진들한테 꼰지름 
		}
	}
	
	public void setValue(int value) {
		this.value = value;
		notifyObservers(); //값이 바뀌면 바로 꼰지름 
	}
}