package __2장.객체_생성과_파괴.__3.method3_enum;

public enum Singleton {
	INSTANCE;
	
	String test;
	
	public void print(){
		test = "test";
		System.out.println(test);
	}

}

/*

enum singleton - 싱글턴 구현 가장 좋은 방법.

---
장점

1. reflection에 AccessibleObject, setAccessible 메서드으로부터 안전 
2. 위에 방식1,2로 구현하면 직렬화 할 때 문새 발생하는데, enum은 직렬화에 문제 발생 안함. 
	일반 방식은 직렬화 할 때, implements Serializable 하는 것 만으로는 부족함. 
	싱글턴 특성 유지하려면, 모든 필드를 transient 먹이고 readResolve 메서드 추가해야함.
	골치아프게 다른거 쓰지말고 싱글턴 쓸꺼면 enum singleton 쓰자.
*/