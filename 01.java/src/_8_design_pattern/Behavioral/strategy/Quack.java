package _8_design_pattern.Behavioral.strategy;

public class Quack implements QuackBehavior{

	@Override
	public void quack() {
		System.out.println("quack!");
	}
}

