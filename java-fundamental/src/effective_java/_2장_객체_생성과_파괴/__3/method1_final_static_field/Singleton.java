package effective_java._2장_객체_생성과_파괴.__3.method1_final_static_field;

public class Singleton {
	public static final Singleton INSTANCE = new Singleton();
	
	private Singleton() { //private constructor to prevent new
		if(INSTANCE != null){
			throw new RuntimeException("생성자를 호출할 수 없습니다!");
		}
	} 

	public void leaveTheBuilding() {}
}

/*

singleton 생성 방법1 - static final field


---
특징

final이라 객체가 단 한개만 생성됨.
private 생성자니까 new로 객세 생성은 못함.
따라서 새 객체 만들면 저 INSTANCE객체도 같이 생겨난다는건데, 새 객체 생성 못한다는 말이니까, 저 INSTANCE객체는 한개가 보장된다는 것.

Singleton.INSTANCE로 객체 가져오는 방법.


 
---
단점


refleciton에 AccessibleObject.setAccessible로 private 깰 수 있음 -> 위험
-> 그래서 private 생성자에서, reflection으로 private 생성자를 깨고 들어오려고 할 때, throw new Exception 하면 reflection 방어 가능.



*/
