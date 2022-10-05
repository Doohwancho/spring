package functional.functional_interface.function;

public class Main {

    public static void main(String[] args) {
        Function<Integer, Integer> multiplyFunction = number -> number * number; //Function<T,R>에서, T인 Integer인 number로 받아서, R인 Integer인 number*number로 반환
        System.out.println(multiplyFunction.apply(10)); //100

        Function<Integer, Integer> minusFunction = number -> number - 10;
        Integer result = multiplyFunction.andThen(minusFunction).apply(10);
        System.out.println(result); //90 
    }
}

/*

---
what is Function?


Function<T,R> : <T> -> <R>



Function<A,B> 이면, () -> ? 일 때, 
저 () 람다로 받는 타입은 A이고, ?의 반환타입은 B인듯?


주로 Function<T,R>은 일급함수, 고차함수 가지고 놀 때 쓰는듯?


근데 다른 함수형 언어는 타입 명시가 없으니까 Function<T,R> 안써도 되니까 함수형 식이 깔끔하게 쓰이는데,
자바는 저게 덕지덕지 붙어있어서 미관상 이쁘진 않은듯 =_=;;


*/