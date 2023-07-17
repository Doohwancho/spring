package design_pattern.Behavioral.iterator;

public class Waitress {
	
	Menu saladRestaurant;
	Menu meatRestaurant;
	
	public Waitress(Menu saladRestaurant, Menu meatRestaurant) {
		this.saladRestaurant = saladRestaurant;
		this.meatRestaurant= meatRestaurant;
	}
	
	public void prepareMeal() {
		saladRestaurant.prepareMeal();
		meatRestaurant.prepareMeal();
	};
	
	public void listIngredient() {
		
		/*
		Object[] salad = saladRestaurant.getMeal(); 
		List meat = meatRestaurant.getMeal();
		
		for(int i = 0; i < salad.length; i++) {
			System.out.println(salad[i]);
		}
		
		for(int i = 0; i < meat.size(); i++) {
			System.out.println(meat.get(i));
		}
		*/
		
		Iterator salad = saladRestaurant.createIterator();
		Iterator meat = meatRestaurant.createIterator();
		
		
		loopMeal(salad);
		loopMeal(meat);
	}
	
	public void loopMeal(Iterator iter) { //인자를 Iterator로 받는다. 
		while(iter.hasNext()) { //Object[]와 List 타입이 다른걸 한번에 loop 돌게 해준
			System.out.println(iter.next());
		}
	}
}