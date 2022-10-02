package functional.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.IntFunction;

public class Main {
	public static void main(String[] args) {
		
		//case1) 
		IntFunction intSum = (x) -> x+1;
		System.out.println(intSum.apply(1));
		
		
		//case2)
		MyFunction f = new MyFunction() { //인터페이스인데 객체 생성이 가능하네?
		    public int max (int a, int b) {
		      return a > b ? a : b;
		    }
		};
		
		
		int big = f.max(5, 3);  //익명 객체의 메서드 호출
		
		MyFunction f2 = (int a, int b) -> a > b ? a : b; //람다로 선
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
		    public int compare(String s1, String s2)  {
		      return s2.compareTo(s1);
		    }
		  });
		  
		// 람다식으로 구현
		  List<String> list2 = Arrays.asList("abc", "aaa", "bbb", "ccc");
		  Collections.sort(list, (s1, s2) -> s2.compareTo(s1)); //same thing as above 
	}
	


}
/*
---
what is lambda?

익명함수.




---
장점


깔끔. 간결. 

함수 이름 불필요한데 안적어도 됨.
function, void, public 이런거 모조리 스킵 가능하네???????

function은 그렇다 쳐.

void랑 public 얘는 어쩔꺼지?

*/