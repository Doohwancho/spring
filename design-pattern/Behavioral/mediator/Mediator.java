package mediator;

public interface Mediator {
	void notify(Component sender, String event); //누가 어떤 이벤트 보냈는지 알려준다. 
}