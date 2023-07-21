package oop._2_동적_late_binding.generics.what.step02_extendsAndSuper;

public class Main {
	public static void main(String[] args) {
		FruitBox<Fruit> fruitBox = new FruitBox<>();
	}
}

/*

---
class hierarchy

- Object
	- Food
		- Fruit
			- Apple
			- Banana
		- Vegetable
			- Carrot


---
만약 Fruitbox에서 <T extends Fruit> 하면?


그 클래스 안에서 쓸 수 있는건, 
Fruit의 자식들 뿐.

1. Fruit
2. Apple
3. Banana


---
만약 Fruitbox에서 <T super Fruit> 하면?


그 클래스 안에서 쓸 수 있는 타입은
Fruit포함 부모들. (형제 아님)

1. Fruit
2. Food
3. Object 

쓸 수 있다.



*/