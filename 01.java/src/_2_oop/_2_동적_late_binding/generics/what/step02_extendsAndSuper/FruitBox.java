package _2_oop._2_동적_late_binding.generics.what.step02_extendsAndSuper;

import java.util.ArrayList;
import java.util.List;

public class FruitBox <T extends Fruit>{
	private List<Fruit> fruits = new ArrayList<>();
	
	public void add(T fruit) {
		fruits.add(fruit); //OK. why? T extends Fruit이니까, Fruit을 포함한 자식들 다 담을 수 있거든. 
	}
}
