package _2_oop._2_동적_late_binding.generics.what.step01_basic;

public class GenericBox <T>{
	private T fruit;
	
	public GenericBox(T fruit) {
		this.fruit = fruit;
	}
	
	public T getFruit() {
		return fruit;
	}
}
