package decorator;

public class Espresso extends Beverage{ //얘는 기존 Beverage 객

	public Espresso() {
		description = "Espresso";
	}
	
	@Override
	public double cost() {
		return 2.4;
	}
}
