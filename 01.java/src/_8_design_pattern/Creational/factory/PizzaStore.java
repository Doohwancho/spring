package _8_design_pattern.Creational.factory;

public class PizzaStore {
	
	PizzaFactory factory;
 
	public PizzaStore(PizzaFactory factory) { 
		this.factory = factory;
	}
 
	public Pizza orderPizza(String type) {
		Pizza pizza;
 
		//페페로니 피자이던 치즈 피자이던, 만드는 역할을 Store에서 분리
		//추후 새로운 피자 추가하면, PizzaStore에 if-else문 수정하는게 아니라, Pizza 상속받고 Factory에 추가하면 
		pizza = factory.createPizza(type); 
 
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();

		return pizza;
	}

}