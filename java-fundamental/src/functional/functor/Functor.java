package functional.functor;

import java.util.function.Function;


//case1) 기본형 
//interface Functor<T> {
//    <R> Functor<R> map(Function<T,R> f);
		//함수 f를 받아 f(x)한 후, 다시 Functor<R>로 포장하는 것. 
		//f(x)의 결과값을 새로운 Functor<R>에 담아서 반환하기 때문에, 
		//기존 데이터 x를 건드리지 않음 -> 불변
//}


//case2) Identity<T>가 컴파일 되도록, 타입 인자에 F를 추가한 버전. 
interface Functor<T, F extends Functor<?,?>> { 
    <R> F map(Function<T,R> f);
}
//
