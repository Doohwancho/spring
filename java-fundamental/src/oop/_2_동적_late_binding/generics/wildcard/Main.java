package oop._2_동적_late_binding.generics.wildcard;

import java.util.ArrayList;
import java.util.List;

import oop._2_동적_late_binding.generics.extendsAndSuper.Apple;
import oop._2_동적_late_binding.generics.extendsAndSuper.Banana;
import oop._2_동적_late_binding.generics.extendsAndSuper.Fruit;

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
*/

public class Main {
	public static void main(String[] args) {
		printList(null);
		
//		case3)
		List<? super Fruit> fruits = new ArrayList<>();
		fruits.add(new Apple());
		fruits.add(new Banana());
//		fruits.add(new Food()); //compile error! Q. 왜? super니까 위에 놈들 다 들어가야 하는거 아냐? 
		//A. List<? super Fruit>은 List<Fruit> || List<Food> || List<Object> 셋 다 될 가능성이 있음.
		//근데 만약에 List<Fruit>이 되면, 그것보다 상위 클래스인 Food는 .add()못할거아냐. 그래서 그럼. 
		
		
		//case4) wildcard in method parameter
		List<String> stringList = new ArrayList<>();
		printList2(stringList);
		
		
		//case5) wildcard in method parameter
		List<Integer> ints = new ArrayList<>();
		addDouble(ints);
	}
	
	public static void printList(List <? extends Fruit> fruits) {
		for(Fruit fruit: fruits) {
			System.out.print(fruit+" ");
		}
		
		//case1) 
//		for(Apple fruit: fruits) { //compile error! -> why? Fruit+자식들인 Apple,Banana올 수 있는데, Apple로 확정하면 나머지 Fruit, Banana 못오잖아. 
//			System.out.print(fruit+" ");
//		}
	
//		case2) 만약 파라미터가 List <? super Fruit> 였다면, 무조건 Object로 받아야 컴파일 에러가 안난다.  
//		for(Object fruit: fruits) { //왜냐하면, T super Fruit는 Fruit, Food, Object가 해당되는데, 그 중 세개 다 포괄하는게 Object이기 때문.  
//			System.out.print(fruit+" ");
//		}
	}
	
	
	public static void printList2(List<?> list) { //만약 List<Object>였다면 에러. 왜? List<Object>의 자식은 List<String>이 아니기 때문. 
		for(Object elem : list) { //List<?>의 .get()은 항상 Object타입이어야 한다. 뭔타입 나올지 모르니까. 
			System.out.println(elem+" ");
		}
		System.out.println();
	}
	
	public static void addDouble(List<?> ints) {
//		ints.add(3.14); //compile error! -> ?가 어떤 타입이 올줄알고 더블을 넣는겨! 
		ints.add(null); //따라서 null만 삽입 가능 
	}
}

