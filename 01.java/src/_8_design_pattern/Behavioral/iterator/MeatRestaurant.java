package _8_design_pattern.Behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

public class MeatRestaurant implements Menu {
	private List meal;
	
	Object chicken;
	Object pig;
	Object cow;
	
	public MeatRestaurant() {
		meal = new ArrayList<>();
		chicken = "chicken";
		pig = "pig";
		cow = "cow";
	}
	
	public void addIngredient(Object food) {
		meal.add(food);
	}
	
	public void prepareMeal() {
		addIngredient(chicken);
		addIngredient(pig);
		addIngredient(cow);
	}
	
	public List getMeal() {
		return meal;
	}
	
	public Iterator createIterator() {
		return new MeatIterator(this);
	}
}
