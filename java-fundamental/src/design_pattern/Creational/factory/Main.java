package design_pattern.Creational.factory;

public class Main {

	public static void main(String[] args) {
		PizzaFactory factory = new PizzaFactory();
		PizzaStore store = new PizzaStore(factory); //DI factory into PizzaStore

		Pizza pizza = store.orderPizza("cheese");
		System.out.println("We ordered a " + pizza.getName() + "\n");
		System.out.println(pizza);
 
		pizza = store.orderPizza("pepperoni");
		System.out.println("We ordered a " + pizza.getName() + "\n");
		System.out.println(pizza);
	}
}
