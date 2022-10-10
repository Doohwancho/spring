package singleton.step4;

//Thread-safe Lazy Initialization + Double-checking locking
public class Singleton {
	
	private volatile static Singleton uniqueInstance;
	
	private Singleton() {}
	
	public static Singleton getInstance() {
		if(uniqueInstance == null) {
			synchronized (Singleton.class) {
				if(uniqueInstance == null) {
					uniqueInstance = new Singleton();
				}
			}
		}
		return uniqueInstance;
	}
}

/*

메서드 자체를 synchronized하지 않고, if(instance == null)로 한번 필터링 해주는 법

일단 인스턴스가 생성되어있는지 확인한 다음, 생성되지 않았을 때만 동기화 하는 법.

step3 - thread-safe lazy initialization에서 한단계 더 발전한 것.

volatile키워드를 사용하면 멀티스레딩을 쓰더라도 uniqueInstance 변수가 Singleton 인스턴스를 초기화 하는 과정에서 올바르게 진행되도록 할 수 있다.

주의!

DCL방법은 자바 1.4 버전 이하에서는 쓸 수 없음! volatile키워드가 잘 작동 안하기 때문


*/