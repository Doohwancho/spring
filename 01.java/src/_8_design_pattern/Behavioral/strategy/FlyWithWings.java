package _8_design_pattern.Behavioral.strategy;

public class FlyWithWings implements FlyBehavior{

	@Override
	public void fly() {
		System.out.println("fly with wings!");
	}
}