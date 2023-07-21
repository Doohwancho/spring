package effective_java._2장_객체_생성과_파괴.__3.method2_static_factory;

public class Singleton {
	private static final Singleton INSTANCE = new Singleton();
	private Singleton() {}
	
	public static Singleton getInstance() { return INSTANCE; }

	public void leaveTheBuilding() {}

}


/*

static factory method로 singleton 구현한 방법


method1과의 차별성은
책에서는 generic singleton factory로 만들 수 있고, 인스턴스를 Supplier<>로 감쌀 수 있다는데,
음.. 저것들은 안써봐서 모르겠고


내 레벨에서는 static factory 방식이 직접참조보다 나은점은 
lazy loading 적용할 때 좋다 정도..?
그리고 주입할 때 파라미터로 유효성 체크 가능하다 정도인 듯?


 */

