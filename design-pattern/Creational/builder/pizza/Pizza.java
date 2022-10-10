package builder.pizza;

import java.util.*;

//Entity
public class Pizza { 
	String name;
	List<String> toppings;
	
	//setter
	public void setName(String name) {
		this.name = name;
	}
	
	//setter
	void addToppings(List<String> toppings) {
		this.toppings = toppings;
	}
 
	//func1
	void prepare() {
		System.out.println("Prepare " + name);
		System.out.println("Tossing dough...");
		System.out.println("Adding sauce...");
		System.out.println("Adding toppings: ");
		for (String topping : toppings) {
			System.out.println("   " + topping);
		}
	}
  
	//func2
	void bake() {
		System.out.println("Bake for 25 minutes at 350");
	}
 
	//func3
	void cut() {
		System.out.println("Cut the pizza into diagonal slices");
	}
  
	//func4
	void box() {
		System.out.println("Place pizza in official PizzaStore box");
	}
 


	//toString
	public String toString() {
		StringBuffer display = new StringBuffer();
		display.append("---- " + this.name + " ----\n");
		for (String topping : toppings) {
			display.append(topping + "\n");
		}
		return display.toString();
	}
}

 
 

