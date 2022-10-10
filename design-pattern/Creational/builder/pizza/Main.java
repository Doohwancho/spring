package builder.pizza;

public class Main {

	public static void main(String[] args) { 
		PizzaBuilder veggieBuilder = new VeggieLoversPizzaBuilder();

		//builder pattern: 원하는 토핑 개별추가 
		Pizza veggie = veggieBuilder.addSauce().addCheese().addOlives().addTomatoes().addSausage().build();
		
		veggie.prepare();
		veggie.bake();
		veggie.cut();
		veggie.box();
		System.out.println(veggie);

		
		PizzaBuilder meatBuilder = new MeatLoversPizzaBuilder();

		//builder pattern: 원하는 토핑 개별추가 
		Pizza meat = meatBuilder.addSauce().addTomatoes().addCheese().addSausage().addPepperoni().build();
		
		meat.prepare();
		meat.bake();
		meat.cut();
		meat.box();
		System.out.println(meat);
		
	}
}
