package _2_oop._2_동적_late_binding.generics.what.step01_basic;

public class Main {
	public static void main(String[] args) {
		//case1) Object 사용 
		ObjectBox objBox = new ObjectBox(new Apple());
		Apple apple = (Apple)objBox.getFruit();
		Banana banana = (Banana)objBox.getFruit(); //runtime error! -> Apple extends Object가 들어가있었는데, Banana로 형변환 하려니 런타임 에러. 
		
		
		//case2) Generic 사용 
		GenericBox<Apple> genericBox = new GenericBox<>(new Apple());
		Apple apple2 = genericBox.getFruit();
//		Banana banana2 = genericBox.getFruit(); //compile error! -> 타입 이상한거 컴파일 타임에 바로 잡음! 
		
	}
}

/*

---
장점

1. 컴파일 타임에 안전하게 자료형 오류 검증 
2. Object -> Apple 형변환 줄어듬 -> 코드 가독성 향상
3. typedef 검증 매번 안해도 됨.  


*/