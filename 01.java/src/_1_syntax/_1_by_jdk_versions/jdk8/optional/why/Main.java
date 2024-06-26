package _1_syntax._1_by_jdk_versions.jdk8.optional.why;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import _1_syntax._1_by_jdk_versions.jdk8.optional.Person;


/*

---
why use optional?


sophisticated way of handling 'null' in java


Optional == null까지 감싸는 wrapper class.
stream에서 .method1().method2().method3() ... 이렇게 결과값을 순차반환할 때,
중간에 null이 발생한다면?
Optional쓰면, null이 발생해도 에러안나고 내부적으로 처리해줌.


장점1)
java에서 객체에 값 넣을 때, null checking으로 Objects.requireNonNull(); 가 있긴 한데,
얘도 null이 없을 때 처리를 if~else로 해줘야 해서 코드가 길어짐.

Optional을 쓰면, null이 있냐 없냐 체크만 가능한게 아니라, null이 없을 때 어떻게 할지 .orElse()같은 놈들로 대응 가능.

장점2)
null-safe 해짐 -> 코드의 side-effect 줄어듬


---
주의점!

1. 어찌됬든 wrapper class로 한번 더 감싸는거나 boxing, unboxing 비용이 있고, Optional을 씀으로써 나오는 새로운 에러가 있으니, nullable object 처리할 때에만 한정적으로 쓰자.
2. 직렬화 할 때 왠만하면 쓰지 말자. 예전 Jackson 라이브러리의 경우 Optional 지원 안하는 경우도 있다.
*/

public class Main {



	public static void main(String[] args) {
		//Q. minsu 객체에 name이 null인지 확인한 후에, null이 아니면 값을 반환하는 코드 짜고싶다면?

		//ex1)
		//case1) 넘 불편해~
		String name;
		Person minsu = new Person("minsu");
		
		if(minsu.getName() != null) {
			name = minsu.getName();
		} else {
			name = "hanna";
		}
		System.out.println(name);

		System.out.println("-----------------------------");

		//case2) Optional 쓰면 null 처리 쉬워짐.
		name = Optional.of(minsu).map(Person::getName).orElse("hanna");
		System.out.println(name);

		System.out.println("================================");


		//ex2)
		ArrayList<Integer> list = new ArrayList<>();

		//before java8
		List<Integer> tempNames = list != null ? list : new ArrayList<>();

		//after java8
		List<Integer> nameList = Optional.ofNullable(tempNames).orElseGet(() -> new ArrayList<>());

		System.out.println("================================");

	}
}