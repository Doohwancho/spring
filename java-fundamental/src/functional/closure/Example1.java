package functional.closure;

import java.util.function.Supplier;

public class Example1 {

    static void closure() {
        final var supplier = outerMethod(); //outerMethod() 호출 후, 소멸 
        System.out.println(supplier.get()); //근데 .get()해보면 local variable 값을 꺼낼 수 있음?!
    }
 
    private static Supplier<String> outerMethod() {
        String str = "outer method local variable";
        return () -> str;
        
        //case1) closure context 내부에선 final str로 만들어져서 복사된다.
//        return () -> {
//            str = "aa"; //ERROR! -> closure context안 str이 final String으로 만들어지기 때문
//            return str;
//        };
        
        //case2) () -> str;은 내부적으로 이와 같다. 
//        return new Supplier<String>() {
//            @Override
//            public String get() {
//                return str;
//            }
//        };
    }
    
    public static void main(String[] args) {
		closure(); //outer method local variable
	}
}


/*

---
what is closure?


함수 끝났는데 outer function scope에 접근 가능.




---
Q. 어떻게 outerMethod()가 끝났는데, 로컬변수 .get()가능?


-> Supplier를 get()하면, 종료된 함수의 로컬변수 가져올 수 있음.

함수가 종료되기 전, 클로저가 생성될 때, 함수 자체가 복사되어 따로 컨텍스트 유지함.

클로저란, 익명클래스에 컨텍스트를 넘겨주는 것.


*/