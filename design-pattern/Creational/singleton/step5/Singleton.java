package singleton.step5;

//Initialization on demand holder idiom
public class Singleton {
	private Singleton() {}
	
	private static class SingletonHolder {
		public static final Singleton INSTANCE = new Singleton();
	}
	
	public static Singleton getInstance() {
		return SingletonHolder.INSTANCE;
	}
}

/*

현제 업계에서 가장 많이 사용되는 방법이라고 한다.


클래스 안에 클래스(Holder)를 두어 JVM의 Class Loader 메커니즘과 Class가 로드되는 시점을 이용한 방식입니다.
Lazy initialization 장점을 가져가면서 Thread 간 동기화 문제를 동시에 해결한 방법입니다.
 


SingletonByIdiom 클래스는 클래스 로드 시점에 초기화되지만 정적 클래스로 정의된
내부 클래스의 초기화는 해당 시점에 이뤄지지 않는 특성이 있습니다.

 
즉 getInstance를 통해 내부 클래스의 instance를 호출할 때 뒤늦게 초기화되어 객체를 할당한다.
이 방식이 thread safe 한 이유는 jvm의 클래스 초기화 과정에서 보장되는 원자적 특성(시리얼 하게 동작)을 이용하기 때문이다. 
즉 동기화 문제를 jvm이 처리하도록 합니다.

*/