package decorator;


public class Mocha extends CondimentDecorator{ //얘는 decorator로 추가된

	Beverage beverage;
	
	public Mocha(Beverage beverage) {
		this.beverage = beverage;
	}
	
	@Override
	public String getDescription() {
		return beverage.getDescription()+" mocha";
	}

	@Override
	public double cost() {
		return 1.1 + beverage.cost();
	}
	
}
