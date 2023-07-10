package oop._2_동적_late_binding.generics.step01_basic;

public class ObjectBox {
	private Object fruit;
	
	public ObjectBox(Object fruit) {
		this.fruit = fruit;
	}
	
	public Object getFruit() {
		return fruit;
	}
}
