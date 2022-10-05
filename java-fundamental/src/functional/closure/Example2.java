package functional.closure;

import java.util.function.Function;

public class Example2 {
	/** case2) 함수를 결과로 반환하는 함수 */
	public static final Function<String, Function<String, String>> GREETING1 = message -> name -> message + ", " + name + '!';
	
	//GREETING1, GREETING2는 같은 것
	public static final Function<String, Function<String, String>> GREETING2 = (message) -> {
		return (name) -> { //closure -> 기존에 greetingText를 기억함.  내부 {}의 message가 외부 {}의 message에 접근함.
			return message + ", " + name + "!";
		};
	};

	public static void main(String[] args) {
		Function<String, String> koreanGreeting = GREETING2.apply("안녕"); 
		Function<String, String> englishGreeting = GREETING2.apply("Hello");

		System.out.println(koreanGreeting.apply("세계")); // 안녕, 세계!
		System.out.println(englishGreeting.apply("Hello")); // Hello, world!
	}
	
	//closure가 어떤 방식으로 동작하지?
	//맨 처음에 GREETING2.apply("안녕"); 할 땐, "안녕"이 outer {}의 message로 가서 저장된 이후, closure라서 외부 {}가 종료되도 값이 저장되고 있는데, 
	//koreanGreeting.apply("세계") 했을 때, "세계"가 inner {}의 name에 가서 둘이 출력되는건가? 
	//그런듯 
}
