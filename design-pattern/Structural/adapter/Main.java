package adapter;

public class Main {
	public static void main(String[] args) {
		Duck duck = new MallardDuck();

		Turkey turkey = new WildTurkey();
		Duck turkeyAdapter = new TurkeyAdapter(turkey); //어답터 먹이면 turkey가 Duck type으로 호환

		System.out.println("The Turkey says...");
		turkey.gobble();
		turkey.fly();

		System.out.println("\nThe Duck says...");
		testDuck(duck);

		System.out.println("\nThe TurkeyAdapter says...");
		testDuck(turkeyAdapter);
	}

	static void testDuck(Duck duck) {
		duck.quack();
		duck.fly();
	}
}

/*

기존 시스템, 코드를 그대로 유지하면서, 새로운 인터페이스와 병합시 호환문제 해결.
220v -> 110v 돼지코와 같은 역할

---
diff adapter vs decorator

adaptor: 다른 인터페이스 끼리 호환성 문제 해결하려고 한 인터페이스를 다른 인터페이스로 변환함. 
decorator: 기존 인터페이스 바꾸지 않고 새로운 기능(책임)만 추가하는 것 


*/