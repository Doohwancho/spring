package __2.객체_생성과_파괴.__4;

public class UtilityClass {
	
	// 기본 생성자가 만들어지는 것을 막는다(인스턴스화 방지용)
	private UtilityClass(){
		throw new AssertionError();
	}
}

/*

인스턴스 못만들게 막고싶으면,
abstract class로 만들지 말고(상속해서 객체 생성 가능하니까)
private constructor 만들고, throw new Exception()을 넣어라.

이렇게 하면 상속도 막을 수 있다.
자식 생성자에서 super()하면 에러나니까.


final class로 안만들어도 자식클래스 생성 불가능하게 하는 방법이 이거네? 

*/