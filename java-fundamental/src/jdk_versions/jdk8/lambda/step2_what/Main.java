package jdk_versions.jdk8.lambda.step2_what;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.IntFunction;

/*
	Q. what is lambda?

	1. (List<String> list) -> list.isEmpty()
	2. (Apple a) -> {
	3. (Apple a ) -> {
       Sysmte.out.println(a.getWeight());
   }
	4. (String s) -> s.length()
	5. (int a, int b) -> a * b
 */
public class Main {

	public static void main(String[] args) {

		//case1) java에서 제공하는 함수 인터페이스
		IntFunction intSum = (x) -> x + 1;
		System.out.println(intSum.apply(1));

		//case2) 내가 커스텀으로 만든 함수 인터페이스
		MyFunction f = new MyFunction() { //인터페이스인데 객체 생성이 가능하네?
			public int max(int a, int b) {
				return a > b ? a : b;
			}
		};

		int big = f.max(5, 3);  //익명 객체의 메서드 호출

		MyFunction f2 = (int a, int b) -> a > b ? a : b; //람다 (MyFunction은 인터페이스로 따로 정의해야 함)
		int big2 = f2.max(5, 3);

		//case3) - 람다식은 사실...

		// 람다식
//		  (int a, int b) -> a > b ? a : b;

		// 익명클래스의 객체
//		  new Object()  {
//		    int max(int a, int b) {
//		      return a > b ? a : b ;
//		    }
//		  }

		//이 둘은 같다. 

		//case4) example

		// 기존 인터페이스의 메서드 구현
		List<String> list = Arrays.asList("abc", "aaa", "bbb", "ccc");
		Collections.sort(list, new Comparator<String>() {
			public int compare(String s1, String s2) {
				return s2.compareTo(s1);
			}
		});

		// 람다식으로 구현
		List<String> list2 = Arrays.asList("abc", "aaa", "bbb", "ccc");
		Collections.sort(list, (s1, s2) -> s2.compareTo(s1)); //same thing as above
	}

}
