package design_pattern.Creational.singleton.step6;

//ENUM singleton method
enum Singleton {
	UNIQUE_INSTANCE;
 
	// other useful fields here

	// other useful methods here
	public String getDescription() {
		return "I'm a thread safe Singleton!";
	}
}

/*

모든 Enum type은 프로그램 내에서 한번 초기화되는 점을 이용하는 방식

싱글톤의 특징(단 한 번의 인스턴스 호출, Thread간 동기화) 을 가지며 비교적 간편하게 사용할 수 있는 방법입니다.

단 한번의 인스턴스 생성을 보장하며 사용이 간편하고 직렬 화가 자동으로 처리되고, 
직렬화가 아무리 복잡하게 이루어져도 여러 객체가 생길 일이 없으며, 리플렉션을 통해 싱글턴을 깨트릴 수도 없다고 합니다.

(Joshua Bloch가 작성한 effective java 책에서 소개되었다고 합니다.)

*/