package __2장.객체_생성과_파괴.__5;

public class Main {
	public static void main(String[] args) {
		Lexicon lexicon = new KoreanDictionary();
		
		//case1) 유연하지 못한 주입 
//		SpellChecker spellChecker = new SpellChecker(lexicon);
		
		//case2) DI
//		SpellChecker spellChecker = new SpellChecker(lexicon); 
		
		//case3) Supplier<T>
		SpellChecker spellChecker = new SpellChecker(() -> lexicon); //() -> lexicon하는 이유는 Supplier 써서 
		
	}
}


/*

---
structure of code


Lexicon<Interface>
	KoreanDictionary
	EnglishDictionary
	
SpellChecker에서 DI로 Lexicon 객체 생성자 주입 




---
what is this code?



하드코딩으로 객체 주입하지 말고, 
DI로 주입하라는말 같다.



예제에서 굳이 Supplier<T>를 쓴 이유는,
supplier.get()으로 Supplier<T> 클로저에 저장된 매개변수를 선택적으로 넘겨서 팩토리 메서드만들 때 활용하라는 말 같은데,
너무 advanced한 듯 하다. 





---
기존 하드코딩 주입방식 대비 DI의 장점


1. 낮은 결합도 -> flexible
2. 테스트할 때 원하는 객체(mock이든) 맘대로 집어넣을 수 있어서 테스트하기 더 편하고 용이 
3. 여러 객체들이 static Lexion을 만들어서 공유하면, 멀티쓰레드 환경에서 위험할 수도. 반면. 객체 주입받으면, 객체 복사해서 받으니까 불변성이 있어서 여러 메서드가 안심하고 Lexion 쓸 수 있다.
4. 팩토리 메서드 패턴 만들 수 있다. 




*/