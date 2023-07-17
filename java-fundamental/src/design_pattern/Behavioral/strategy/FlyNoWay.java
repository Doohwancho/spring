package design_pattern.Behavioral.strategy;

public class FlyNoWay implements FlyBehavior{

	@Override
	public void fly() {
		System.out.println("I'm not flying. just walking");	
	}	
}