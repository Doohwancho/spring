package functional.monad;

public class Main {
	public static void main(String[] args) {
		// case1)
		Wrap<Integer> a = Wrap.of(1); // Wrap(value = 1)
		Wrap<Integer> b = a.map(i -> i + 9); // Wrap(value = 10)
		Wrap<Integer> c = b.map(i -> i * 11); // Wrap(value = 110)
		System.out.println(c);
		a.map(i -> i * 10).map(i -> i + 11); // Wrap(value = 21)

		// case2)
		Wrap<Integer> d = Wrap.of(1); // Wrap (value=1)
		d.map(Wrap::inc); // Wrap (value =2)  BUT! .map() 한번만 가능. 
		d.flatMap(Wrap::inc).flatMap(Wrap::inc); // Wrap(value=3)  
		d.customMapUsingFlatMap(Wrap::inc);
		
	}
}

/*

---
what is monad?


수학 카테고리 이론(범주론)에서 비롯.

범주론이란?

집합은 안에 요소가 뭐가있냐가 중요하잖아?
범주론은 대상이 있고 그들의 관계성을 중시함.

f(x) -> y 에서, f == x->y;
g(y) -> z 에서, g == y->z;
therefore,
g(f(x)) -> z


여기서 input,output인 x,y,z에 포커스하는게 아니라, 
그 관계인 f,g에 포커스 나온 것.






*/