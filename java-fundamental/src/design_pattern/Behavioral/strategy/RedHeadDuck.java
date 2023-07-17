package design_pattern.Behavioral.strategy;

public class RedHeadDuck extends Duck{

	public RedHeadDuck(String name) {
		super(name);
		flyBehavior = new FlyNoWay(); //전략적으로 내가 원하는 패턴 넣는 것 
		quackBehavior = new Quack();
	}

	@Override
	public void display() {
		System.out.println("redhead duck here!");
	}
}
