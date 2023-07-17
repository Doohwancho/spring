package design_pattern.Creational.builder.pizza;

import java.util.ArrayList;
import java.util.List;

public abstract class PizzaBuilder {
	String name;
	List<String> toppings = new ArrayList<String>();
	
	//자식 builder는 취향에 맞게 toppings에 객체를 주입
	public abstract PizzaBuilder addCheese();
	public abstract PizzaBuilder addSauce();
	public abstract PizzaBuilder addTomatoes();
	public abstract PizzaBuilder addGarlic();
	public abstract PizzaBuilder addOlives();
	public abstract PizzaBuilder addSpinach();
	public abstract PizzaBuilder addPepperoni();
	public abstract PizzaBuilder addSausage();
	
	public Pizza build() {
		Pizza pizza = new Pizza();
		pizza.setName(this.name);
		pizza.addToppings(toppings);
		return pizza;
	}
}
