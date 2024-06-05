package _8_design_pattern.Creational.factory;


public class PizzaFactory {
	
	public Pizza createPizza(String type) { //simply create object to DI
		Pizza pizza = null;

		if (type.equals("cheese")) { //based on parameter
			pizza = new CheesePizza();
		} else if (type.equals("pepperoni")) {
			pizza = new PepperoniPizza();
		}
		return pizza;
	}
}
