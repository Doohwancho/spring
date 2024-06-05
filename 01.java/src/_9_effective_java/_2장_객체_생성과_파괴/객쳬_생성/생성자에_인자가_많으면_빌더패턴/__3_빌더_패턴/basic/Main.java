package _9_effective_java._2장_객체_생성과_파괴.객쳬_생성.생성자에_인자가_많으면_빌더패턴.__3_빌더_패턴.basic;

public class Main {
	public static void main(String[] args) {
		NutritionFacts cocaCola = new NutritionFacts
			.Builder(240, 8)
			.calories(100)
			.sodium(35)
			.carbohydrate(30)
			.build();
	}
}

/*

---
장점

1. new NutritionFacts(240,8,100,35,30); 이러면 어느 필드에 얼마나 들어갔는지 직관적이지 못하는데, 빌더패턴 쓰면 직관적임
2. 불변성 (setter가 없는 한) -> 객체 사용 도중 변할 염려 없음 -> 안정성 보장. 



---
단점

빌더 패턴을 위한 스태틱 클래스 만드는게 은근 장황하기 때문에,
오히려 무난한 점증적 생성자 패턴보다 코드가 더 난잡해 질 수 있다. 
배보다 배꼽이 더 큰 상황. 
고로, 생성자 매개변수가 4개 이상일 때에만 쓰자. 

*/