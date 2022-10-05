package functional.high_order_function;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class HighOrderFunction {

	/** case1) 함수를 인자로 하여 호출할 수 있는 함수 */
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(3, 4, 6, 7, 9);

		// Passing a function as lambda expression
		Collections.sort(numbers, (a, b) -> {
			return a.compareTo(b);
		}); // 인자에 함수 전달 -> 고차함수 조건 1 충족
		System.out.println(numbers);

		Comparator<Integer> comparator = (a, b) -> {
			return a.compareTo(b);
		};
		Comparator<Integer> reverseComparator = comparator.reversed();

		// Passing a function
		Collections.sort(numbers, reverseComparator); //// 인자에 함수 전달 -> 고차함수 조건 1 충족
		System.out.println(numbers);
	}

	/** case2) 함수를 반환하는 함수 */
//	public static void main(String[] args) {
//		Function<Integer, Integer> addOne = adder(1);
//		Function<Integer, Integer> addTwo = adder(2);
//		Function<Integer, Integer> addThree = adder(3);
//
//		// result = 4 + 1 = 5
//		Integer result = addOne.apply(4);
//		System.out.println(result); //5
//
//		// result = 4 + 2 = 6
//		result = addTwo.apply(4);
//		System.out.println(result); //6
//
//		// result = 4 + 3 = 7
//		result = addThree.apply(4);
//		System.out.println(result); //7
//	}
//
//	// adder - High Order Function
//	// returns a function as lambda expression
//	static Function<Integer, Integer> adder(Integer x) {
//		return y -> y + x;
//	}

	/** case3) 함수를 인자로 하여 호출할 수 있고 결과로 함수를 반환하는 함수 */
//	public static BiFunction<Integer, Integer, Integer> add = (valueX, valueY) -> valueX + valueY;
//	public static BiFunction<BiFunction<Integer, Integer, Integer>, Integer, Function<Integer, Integer>> curry = (fx, valueX) -> valueY -> fx.apply(valueX, valueY);
//
//	public static void main(String[] args) {
//		Function<Integer, Integer> addTen = curry.apply(add, 10);
//
//		System.out.println(addTen.apply(5)); // 15
//	}

	// 이건 머리아프니 나중에 커리볼때 봐야지..
}

/*
 * 
 * --- 
 * what is high order function? (고차함수)
 * 
 * 
 * 고차함수는 다음 이 두가지 조건 중, 하나라도 만족되면 고차함수.
 * 
 * 1. 함수를 인자에 할당 가능해야 함. 
 * 2. 함수를 return문에 반환 가능해야 함.
 * 
 * 
 * 
 * 
 * example in javascript)
 * 
 * const increase = value => value + 1; const apply = (fx, value) => fx(value);
 * //함수를 인자로 받음.(fx) + 함수를 반환함.
 * 
 * console.log(apply(increase, 9)); // 10
 * 
 * 
 * 근데 위에 예제는 자바에서 하려니까 Function<String, Function<String, String>> 이런 애들이 들어가서
 * 어려워 보이네.
 * 
 * 
 * 
 * 
 * 
 * --- 
 * why high order function important in functional language?
 * 
 * 
 * 함수 재사용성 때문인 듯.
 * 
 * 
 * 마찬가지로, 함수형 프로그래밍에서는 recursion을 좋아함.(성능이 그닥 좋진 않지만...)
 * 
 * 
 * 
 */
