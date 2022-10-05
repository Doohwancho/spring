package functional.theory.immutable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImmutableTest {
	public static void main(String[] args) {
		final String name = "apple";
//		name = "apple2"; //compile error
		System.out.println(name); //name is immutable object, due to 'final' keyword

		final List<String> alphabets = Arrays.asList("a", "b", "c"); //immutable하게 List 선언하는 방법. 

		//but change value
		alphabets.add("d"); //ERROR! -> Exception in thread "main" java.lang.UnsupportedOperationException
	}
	
	public static List<String> add(List<String> strings, String text) {
	    List<String> dest = new ArrayList<>();
	    for (String string : strings) { //overhead 리스트를 순회하면서 원소들을 복사한다. (DeepCopy) 
	        dest.add(string);
	    }

	    dest.add(text);
	    return dest;
	}
}

/*

불변객체 = 한번 데이터 만들면, 변경불가.

input -> computation -> output에서

인자로 받은 데이터는 call by value여야 하고,
call by reference여도, 기존 객체를 건드리지 않아야 함.
기존객체 쓰려면 기존객체를 새 객체에 deep copy해서 새 객체 써야 함. 
왜냐면 기존객체 참조하던 다른 함수들이 피볼 수 있으니까. 

predictability 높혀서 예측가능하게 하고 신뢰가능하게 하기 때문에 불변객체 사용.
-> 인자 데이터 안건드림 -> no side effect -> immutable, stateless -> great for multi-thread env


ex. 
만약 기존에 List에 원소를 추가하고싶으면?

기존 List를 새로운 List 객체에 deepcopy한 후, 원소 추가해서 새로운 객체 반환함. 
-> 기존 객체를 안건들이니까, 기존 객체 참조하던 다른 메서드들은 안심하고 사용 가능 

*/