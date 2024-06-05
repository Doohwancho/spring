package _8_design_pattern.Creational.singleton.step1;

//eager initialization method
class Singleton {
	
	//static = 1 instance only guarantees
	//static 메서드서 밖에 static 변수 사용 가능 
	private static Singleton uniqueInstance = new Singleton();
	
	//private 선언 
	private Singleton() {}
	
	public static Singleton getInstance() {
		return uniqueInstance;
	}
}


/*
장점: 
클래스 로더에 의해 클래스가 로딩될 때, 싱글톤 객체가 생성됨. 
클래스가 최초 로딩될 때 객체가 생성되므로, thread-safe하다.

단점: 
싱글톤 객체의 사용유무에 관계없이, 클래스가 로드될때마다 싱글톤 객체가 생성되고 메모리 잡기때문에 비효율. 
만약 싱글턴이 메모리 많이 잡아먹는데, 싱글턴이 프로그램 시작 후 아주 늦게 호출되는 상황이라면? 
*/