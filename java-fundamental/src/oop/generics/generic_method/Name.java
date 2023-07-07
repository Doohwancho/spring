package oop.generics.generic_method;

public class Name <T> { //주의! 클래스의 T와 메서드의 T가 따로논다!
	
	private T t;
	
	//case1)
	public T getClassGenericType(T t) { //그냥 T는 클래스의 T를 그대로 쓰겠다는 것. 
		return t;
	}
	
	
	//case2)
	//generic type + return type 둘 다 있으니까, 이 메서드는 generic method 
	//<T>가 붙으면 클래스의 T를 쓰지 않겠다는 것. 메서드 고유의 로컬 T 선언과도 같다. 
	public static <T> void printClassName(T t) { //주의! 클래스의 T와 메서드의 T가 따로논다!
		//Q. <T>에 warning: the type parameter T is hiding the type T! 이거 뭐임?
		//A. 클래스에 붙은 T랑 얘가 다르다고. 
		System.out.println(t.getClass().getName());
	}
	

	//case3) <S>로 로컬 S 제네릭 타입 선언했지만, 리턴 타입은 T인 경우. 
	public <S> T takeSandReturnT(S s) {
		System.out.println(s);
		return t;
	}
}
