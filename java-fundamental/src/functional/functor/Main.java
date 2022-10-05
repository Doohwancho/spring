package functional.functor;

public class Main {
	public static void main(String[] args) {
		Identity<String> idString = new Identity<>("abc");
		Identity<Integer> idInt = idString.map(String::length); //.map()을 이용해 값 변경. 
		
		
		//example) 이렇게 사용도 가능 	
//		Identity<byte[]> idBytes = new Identity<>(customer)
//			    .map(Customer::getAddress)
//			    .map(Address::street)
//			    .map((String s) -> s.substring(0,3))
//			    .map(String::toLowerCase)
//			    .map(String::getBytes);
	}
}

/*

---
what is functor?


functor란, 

f(x) -> y, f == x->y;
얘를 다른 context로 대응하는 방법.
Ff(fx) -> fy, Ff == fx->fy;


???
복사임?

카테고리 f에 있던 동작을, 카테고리 Ff에 고대로 옮긴 것.

example) map

list.map(x -> x+1)할 때,
list를 map context안에 고대로 옮기고
거기에 x+1도 옮겨서 연산하잖아.

다른 컨텍스트로 고대로 옮기는걸 Functor라고 함.




---
functor의 장점 

1. 여러 자료 구조를 추상화하여 내부 구현을 감추고,
2. 일관되고 사용하기 쉬운 API를 제공



*/