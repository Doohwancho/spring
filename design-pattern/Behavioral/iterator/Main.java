package iterator;

public class Main {
	public static void main(String[] args) {
		Waitress waitress = new Waitress(new SaladRestaurant(), new MeatRestaurant());
		waitress.prepareMeal();
		waitress.listIngredient();
	}
}
