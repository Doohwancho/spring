package functional.lambda;

import java.util.Comparator;
import java.util.function.IntFunction;

public class Main<T> {
	public static void main(String[] args) {
		
		IntFunction intSum = (x) -> x+1;
		System.out.println(intSum.apply(1));
		
		
//		() -> {};
//		() -> "Raoul";
//		() -> { return "Mario"; };
//		(Integer i) -> return "Alan" + i; // {return "Alan" + i;} 가 와야 합니다
//		(String s) -> {"Iron Man";}; 
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