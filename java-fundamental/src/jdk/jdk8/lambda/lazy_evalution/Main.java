package jdk.jdk8.lambda.lazy_evalution;

import java.util.function.Supplier;

public class Main {
	public static void main(String[] args) {
		String queryString = "password=test";

		//case1) eager evalution
//		System.out.println(checkInEagerWay(hasName(queryString), hasPassword(queryString)));
		
		//---
		//console.log
		  
		//Checking name: 
		//Checking password: 
		//failed.
		
		//case2) lazy evalution
		System.out.println(checkInLazyWay(() -> hasName(queryString), () -> hasPassword(queryString)));
		
		//---
		//console.log
		
		//Checking name: 
		//failed.
		
	}

	private static boolean hasName(String queryString) {
		System.out.println("Checking name: ");
		return queryString.contains("name");
	}

	private static boolean hasPassword(String queryString) {
		System.out.println("Checking password: ");
		return queryString.contains("password");
	}

	private static String checkInEagerWay(boolean result1, boolean result2) {
		return (result1 && result2) ? "all conditions passed" : "failed.";
	}

	private static String checkInLazyWay(Supplier<Boolean> result1, Supplier<Boolean> result2) {
		return (result1.get() && result2.get()) ? "all conditions passed" : "failed.";
	}
}


/*

&& 연산은 앞에꺼 false면 뒤엣거 실행 안되야하는게 당연한거 아닌가?
근데 왜 eager evalution에서는 뒤에꺼까지 실행되지?

-> checkInEagerWay()여기 밖에서 인자 넘겨주기 전에, 이미 hasName(), hasPassword()이 실행된 이후, 결과값 true, false를 넘겨줘서 그런가보네.



반면에 lazy evalution의 경우엔 high order function으로 인해 함수 자체를 인자로 넘겨줘서
함수1 && 함수2 순차실행 하기 때문에, 결과가 저렇게 나온 듯?



 

*/