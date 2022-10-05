package functional.not_iterable;

import java.util.List;

public class NoIteratate {

	public static void main(String[] args) {
		List<Integer> numbers = List.of(1, 2, 3, 4, 5);

		//for loop
		for (int idx = 0; idx < numbers.size(); idx++) {
		    System.out.println(numbers.get(idx));
		}

		//functional
		numbers.forEach((num) -> System.out.println(num));
	}
}

/*


input -> computation -> output에서


input에 넣은 값에 대한 조작이 없이, output에 예측 가능하게 나와야 한다는게, pure function에 핵심 개념이었잖아?

for, while loop도 똑같음.
반복문 안에 input값 변경할 수 있는 코드가 있을 수 잇으니까, 
반복문을 아예 datastructure 안에 넣어버린 것. Iterate pattern 써서 forEach() 같은 놈들로.




*/