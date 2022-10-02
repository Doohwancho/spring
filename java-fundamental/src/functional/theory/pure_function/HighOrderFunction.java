package functional.theory.pure_function;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public class HighOrderFunction {
	
	/** case1) 함수를 인자로 하여 호출할 수 있는 함수 */
//	public static final Function<Integer, Integer> INCREASE = value -> value + 1;
//
//	public static void main(String[] args) {
//		System.out.println(apply(INCREASE, 9)); // 10
//	}
//
//	public static Integer apply(Function<Integer, Integer> fx, int value) {
//		return fx(value); //이상하게 자바에선 안된다. js에선 되는데. 
//	}
	
	
	/** case2) 함수를 결과로 반환하는 함수 */
//	public static final Function<String, Function<String, String>> GREETING1 = message -> name -> message + ", " + name + '!';
//	
//	//GREETING1, GREETING2는 같은 것
//	public static final Function<String, Function<String, String>> GREETING2 = (message) -> {
//		return (name) -> { //closure -> 기존에 greetingText를 기억함.  내부 {}의 message가 외부 {}의 message에 접근함.
//			return message + ", " + name + "!";
//		};
//	};
//
//	public static void main(String[] args) {
//		Function<String, String> koreanGreeting = GREETING2.apply("안녕"); 
//		Function<String, String> englishGreeting = GREETING2.apply("Hello");
//
//		System.out.println(koreanGreeting.apply("세계")); // 안녕, 세계!
//		System.out.println(englishGreeting.apply("Hello")); // Hello, world!
//	}
	
	//closure가 어떤 방식으로 동작하지?
	//맨 처음에 GREETING2.apply("안녕"); 할 땐, "안녕"이 outer {}의 message로 가서 저장된 이후, closure라서 외부 {}가 종료되도 값이 저장되고 있는데, 
	//koreanGreeting.apply("세계") 했을 때, "세계"가 inner {}의 name에 가서 둘이 출력되는건가? 
	//그런듯 
	
	
	/** case3) 함수를 인자로 하여 호출할 수 있고 결과로 함수를 반환하는 함수 */
//	public static BiFunction<Integer, Integer, Integer> add = (valueX, valueY) -> valueX + valueY;
//	public static BiFunction<BiFunction<Integer, Integer, Integer>, Integer, Function<Integer, Integer>> curry = (fx, valueX) -> valueY -> fx.apply(valueX, valueY);
//
//	public static void main(String[] args) {
//		Function<Integer, Integer> addTen = curry.apply(add, 10);
//
//		System.out.println(addTen.apply(5)); // 15
//	}
	
	//이건 머리아프니 나중에 커리볼때 봐야지.. 
}



/*

---
what is high order function? (고차함수)


고차함수란? -> 함수를 인자로 받거나 반환 가능한 함수.

ex. greeting, hello, hi 를 print 찍으면 함수가 반환된다.




example in javascript)

const increase = value => value + 1;
const apply = (fx, value) => fx(value);

console.log(apply(increase, 9)); // 10


근데 위에 예제는 자바에서 하려니까 Function<String, Function<String, String>> 이런 애들이 들어가서 어려워 보이네.





---
why high order function important in functional language?


함수 재사용성 때문인 듯.


*/
