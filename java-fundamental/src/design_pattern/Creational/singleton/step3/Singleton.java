package design_pattern.Creational.singleton.step3;

//Thread-safe Lazy Initialization
class Singleton {
	
	private static Singleton uniqueInstance;
	
	private Singleton() {}
	
	public static synchronized Singleton getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new Singleton();
		}
		return uniqueInstance;
	}
}

/*

step2 - lazy initialization에서 멀티쓰레드에서 여러쓰레드가 동시접근하는걸 synchronized로 막아줌

단점: 
synchronized사용할 경우, 자바 내부적으로 해당 영역이나 메서드를 lock, unlock처리하기 때문에, 내부적으로 많은 cost가 발생한다. 
(일반 메서드보다 성능이 약 100배 떨어진다고 함)

따라서 많은 thread들이 getInstance()를 호출하면, 그만큼 성능저하가 나타난다.
멀티 쓰레드 환경에서 동기화 문제를 해결하는 대신 속도 문제가 생김

사실 동기화가 꼭 필요한 시점은 이 메서드가 시작되는 때 뿐이라, 
바꿔 말하면 **일단 싱글턴 instance변수에 인스턴스를 대입하고 나면 굳이 이 메서드를 동기화된 상태로 유지시킬 필요가 없다.**

첫번째 과정을 제외하면 동기화는 불필요한 오버헤드만 증가시킬 뿐...

*/