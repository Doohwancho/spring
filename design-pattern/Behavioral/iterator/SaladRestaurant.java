package iterator;

public class SaladRestaurant implements Menu{
	private Object[] meal;
	private int index;
	
	Object grass;
	Object chickenWing;
	Object egg;
	
	public SaladRestaurant() {
		meal = new Object[3];
		index = 0;
		grass = "grass";
		chickenWing = "chickenWing";
		egg = "egg";
	}
	
	public void addIngredient(Object food) {
		meal[index++] = food;
	}
	
	public void prepareMeal() {
		addIngredient(grass);
		addIngredient(chickenWing);
		addIngredient(egg);
	}
	
	public Object[] getMeal() {
		return meal;
	}
	
	public Iterator createIterator() {
		return new SaladIterator(this);
	}
}
