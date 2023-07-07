package oop.generics.basic;

public class GenericBox <T>{
	private T fruit;
	
	public GenericBox(T fruit) {
		this.fruit = fruit;
	}
	
	public T getFruit() {
		return fruit;
	}
}
