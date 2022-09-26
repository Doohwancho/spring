package generics.generic_method;

public class Name <T> { //주의! 클래스의 T와 메서드의 T가 따로논다!
	
	public <T> void printClassName(T t) { //주의! 클래스의 T와 메서드의 T가 따로논다!
		System.out.println(t.getClass().getName());
	}
}
