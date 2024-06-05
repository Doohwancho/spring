package _8_design_pattern.Structural.decorator;

public class Whip extends CondimentDecorator{ //얘는 decorator로 추가된 
	
	Beverage beverage;
	
	public Whip(Beverage beverage) {
		this.beverage = beverage;
	}
	@Override
	public String getDescription() {
		return beverage.getDescription()+" whip";
	}

	@Override
	public double cost() {
		return 0.25 + beverage.cost();
	}
	
}
