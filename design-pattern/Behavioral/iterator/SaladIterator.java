package iterator;

public class SaladIterator implements Iterator{
	
	SaladRestaurant saladRestaurant;
	Object[] meal;
	int idx;
	
	public SaladIterator(SaladRestaurant saladRestaurant) {
		this.saladRestaurant = saladRestaurant;
		meal = saladRestaurant.getMeal();
		idx = 0;
	}

	@Override
	public boolean hasNext() {
		return idx < meal.length;
	}

	@Override
	public Object next() {
		return meal[idx++];
	}

}
