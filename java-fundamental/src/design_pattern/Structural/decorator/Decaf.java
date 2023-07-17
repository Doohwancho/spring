package design_pattern.Structural.decorator;

public class Decaf extends Beverage{ //얘는 기존 Beverage 객

	public Decaf() {
		description = "Decaf";
	}
	
	@Override
	public double cost() {
		return 1.25;
	}
}
