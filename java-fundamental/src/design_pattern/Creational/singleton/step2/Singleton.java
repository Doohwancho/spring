package design_pattern.Creational.singleton.step2;

//Lazy Initialization method
public class Singleton {
	
	private static Singleton uniqueInstance;
 
	private Singleton() {}
 
	public static Singleton getInstance() {
		if (uniqueInstance == null) { //인스턴스 없을 때만 선언 
			uniqueInstance = new Singleton();
		}
		return uniqueInstance;
	}
}

/*

클래스가 로드되도, 싱글턴 인스턴스 안만들다가, getInstance()가 호출되면 그제서야 만드는 방법.

장점: 
메모리 누수 방지

단점: 
multi-thread환경에서 두번 호출될 경우, 인스턴스가 두번 생성될 여지가 있음. 

단점: 
not thread safe

 */