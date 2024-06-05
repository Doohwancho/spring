package _8_design_pattern.Behavioral.strategy;

public class Squeak implements QuackBehavior{

	@Override
	public void quack() {
		System.out.println("squeak!");
	}
}