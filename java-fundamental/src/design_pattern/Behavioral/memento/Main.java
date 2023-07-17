package design_pattern.Behavioral.memento;

public class Main {
   public static void main(String[] args) {
	      Originator originator = new Originator();
	      CareTaker careTaker = new CareTaker();
	      originator.setState("State #1");
	      originator.setState("State #2");
	      careTaker.add(originator.saveStateToMemento()); //new Memento() 죽어도 안하네?
	      originator.setState("State #3");
	      careTaker.add(originator.saveStateToMemento());
	      originator.setState("State #4");

	      System.out.println("Current State: " + originator.getState());		
	      originator.getStateFromMemento(careTaker.get(0)); //VCS마냥 과거 Memento의 상태를 알려줌. 
	      System.out.println("First saved State: " + originator.getState());
	      originator.getStateFromMemento(careTaker.get(1));
	      System.out.println("Second saved State: " + originator.getState());
	   }
}

/*

memento: reminder of past events

VSC for object

*/