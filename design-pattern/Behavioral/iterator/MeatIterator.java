package iterator;

import java.util.List;

public class MeatIterator implements Iterator{

	MeatRestaurant meatRestaurant;
	List meal;
	int idx;
	
	public MeatIterator(MeatRestaurant meatRestaurant) {
		this.meatRestaurant = new MeatRestaurant();
		meal = meatRestaurant.getMeal();
		idx = 0;
	}
	
	@Override
	public boolean hasNext() {
		return idx < meal.size();
	}

	@Override
	public Object next() {
		return meal.get(idx++);
	}

}
