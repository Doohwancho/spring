package _10_functional.compose;

import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
	public static void main(String[] args) {

		// example1)
		Predicate<String> hasName = text -> text.contains("name");
		Predicate<String> hasPassword = text -> text.contains("password");
		Predicate<String> hasBothNameAndPassword = hasName.and(hasPassword); // 두개의 함수를 이어붙임
		String queryString = "name=test;password=test";
		System.out.println(hasBothNameAndPassword.test(queryString)); // true

		
		// example2) 
		Function<Integer, Integer> multiply = t -> t * 3;
		Function<Integer, Integer> add = t -> t + 3;
		Function<Integer, Integer> FirstAddThenMultiply = multiply.compose(add); // 
		Function<Integer, Integer> FirstMultiplyThenAdd = multiply.andThen(add);
		System.out.println(FirstAddThenMultiply.apply(4)); //21
		System.out.println(FirstMultiplyThenAdd.apply(4)); //15
	}
}

/*
 * 
 * --- 
 * what is functional composition?
 * 
 * 
 * 
 * 함수형 프로그래밍은 input -> computation -> output에서, computation에서 헛짓거리 하지 말아서 input을
 * 넣었을 때, output이 예측 가능해야 한다고 했잖아?
 * 
 * 저런 function들을 여러개 이어 붙인게 composition.
 * 
 * 
 * 기존 함수 재활용한다는 점에서,
 * 
 * recursion, high order function과 괘를 같이한다. 
 * 
 * 
 */
