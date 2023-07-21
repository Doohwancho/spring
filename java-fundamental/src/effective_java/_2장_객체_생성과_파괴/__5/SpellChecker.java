package effective_java._2장_객체_생성과_파괴.__5;

import java.util.List;
import java.util.Objects;

public class SpellChecker {
	
	//case1) 유연하지 않은 강한 결합 - 비추 
//	private SpellChecker() {} // 객체 생성 방지
//    public static SpellChecker INSTANCE = new SpellChecker();
//	private final Lexicon dictionary = new KoreanDictionary();
    
	
	//case2) DI
//	private final Lexicon dictionary;
//	
//	public SpellChecker(Lexicon dictionary) {
//		this.dictionary = Objects.requireNonNull(dictionary);
//	}
	
	//case3) Supplier<T>
    private final Supplier<Lexicon> dictionary;

    public SpellChecker(Supplier<Lexicon> dictionary) { 
        this.dictionary = Objects.requireNonNull(dictionary);    
    }
	
    public boolean isValid(String word) {
		return false;
	}
    
    public List<String> suggertions(String type) {
    	return null;
    }
}
