package _1_syntax._1_by_jdk_versions.jdk8.lambda.step5_use_case.step1_lazy_evalution.step2;

import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
    	
    	/** case1) eager evalution */ 
        System.out.println(addOrMultiply(true, add(4), multiply(4))); // 8
//        executing add
//        executing multiply -> 실패! 첫번째 조건만족하면, 두번쨰 조건 무시해야 하는데, 두번째 함수도 실행됬음. 왜? eager evaluation이라.
//        8
        System.out.println("-----------------------------");


        System.out.println(addOrMultiply(false, add(4), multiply(4))); // 16
//        executing add
//        executing multiply
//        16
        System.out.println("================================");
        
        
        
        /** case2) lazy evaluation */
        // 클로저 동작하는 람다 표현
        Function<Integer, Integer> add = t -> {
            System.out.println("executing add");
            return t + t;
        };

        // 클로저 동작하는 람다 표현
        Function<Integer, Integer> multiply = t -> {
            System.out.println("executing multiply");
            return t * t;
        };
        // 일반 함수 대신 람다 클로저를 전달한다.
        System.out.println(addOrMultiply(true, add, multiply, 4));
//        executing add -> 성공! 첫번쨰 조건 만족하니까, 두번째 함수는 실행도 안했음!
//        8
        System.out.println("-----------------------------");

        System.out.println(addOrMultiply(false, add, multiply, 4));
//        executing multiply
//        16
    }

    /** case1) eager evalution */ 
    static int add(int x) {
        System.out.println("executing add"); // this is printed since the functions are evaluated first
        return x + x;
    }

    static int multiply(int x) {
        System.out.println("executing multiply"); // this is printed since the functions are evaluated first
        return x * x;
    }

    static int addOrMultiply(boolean add, int onAdd, int onMultiply) {
        return (add) ? onAdd : onMultiply;
    }
    
    
    /** case2) lazy evaluation */
    // 고차 함수다
    static <T, R> R addOrMultiply(
            boolean add, Function<T, R> onAdd,
            Function<T, R> onMultiply, T t
    ) {
        // 자바는 ? 연산자에 대해서는 늦게 실행하기 때문에, 필요한 메소드만 실행된다
        return (add ? onAdd.apply(t) : onMultiply.apply(t));
    }
    
}


/*


---
eager evalution vs lazy evalution


eager evaluation은 밖에서 연수행이 끝나고, 그 결과값을 반환해서,
불필요하게 연산 전부 수행.


lazy evaluation은 함수 안으로 함수 전달해서 필요에 의해 실행. 
함수를 전달할 때, 람다로 전달함. 


*/