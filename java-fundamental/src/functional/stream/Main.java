package functional.stream;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;


public class Main {
	public static void main(String[] args) {
		List<Person> personList = new ArrayList<>();
		personList.add(new Person("짱구", 23, "010-1234-1234"));
		personList.add(new Person("유리", 24, "010-2341-2341"));
		personList.add(new Person("철수", 29, "010-3412-3412"));
		personList.add(new Person("맹구", 25, null));

		//case1) iterate
		// Function.identity는 t -> t, 항상 입력된 인자(자신)를 반환합니다.
		Map<String, Person> personMap = personList.stream()
		        .collect(Collectors.toMap(Person::getName, Function.identity()));
		
		
		//same thing with above
		Map<String, Person> personMap2 = personList.stream()
		        .collect(Collectors.toMap(new Function<Person, String>() {
		            @Override
		            public String apply(Person person) {
		                return person.getName();
		            }
		        }, new Function<Person, Person>() {
		            @Override
		            public Person apply(Person person) {
		                return person;
		            }
		        }));
		
		
		//case2) filter
		Map<String, Person> personFilterMap = personList.stream()
		        .filter(person -> person.getAge() > 24) // 25살 이상만 골라낸다.
		        .collect(Collectors.toMap(Person::getName, Function.identity()));
		
		
		//case3) filter로 null제
		Stream<String> stream = Stream.of("철수", "훈이", null, "유리", null);
		List<String> filteredList = stream.filter(Objects::nonNull)
		        .collect(Collectors.toList());  
		
		
		//case4) filter로 조건찾기 
		Person person = personList.stream()
		        .filter(p -> p.getAge() == 23)
		        .findFirst().get();
		
		
		
		//case5) sort
		personList.stream()
        .sorted(Comparator.comparing(Person::getAge))
        .forEach(p -> System.out.println(p.getName()));
		
		
		
		//case6) reduce
		List<Integer> list = List.of(5, 4, 2, 1, 6, 7, 8, 3);
        
		// 36
		Integer result = list.stream()
		        .reduce(0, (value1, value2) -> value1 + value2);
		
		int intResult = list.stream()
		        // 또는 .mapToInt(x -> x).sum();
		        .mapToInt(Integer::intValue).sum(); //이렇게 하면 박싱 비용 줄일 수 있다. 
		
	}
}

/*

---
1. Structure of Stream



스트림 객체를 뜯어보자.  

public interface Collection<E> extends Iterable<E> {

    default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
    
}

Collection 인터페이스면서, Iterable을 받는다. 


맨 처음에 personList.stream() 할 때, default Stream<E> stream() {}가 되어,
그 다음 타자한테 계속 Stream<E>객체를 넘겨주다가, 
맨 마지막에 .get()이던, .sum()이던, .collect()이던, .count()이던,  스트림 객체에서 primitive type || wrapper class로 형변환 후 반환해준다.



.stream()이후에 붙는건 주로,
1. .collect()
2. .filter()
3. .sort()
4. .reduce()
가 있다.



---
2. :: ???


Map<String, Person> personMap = personList.stream()
        .collect(Collectors.toMap(Person::getName, Function.identity()));
        

여기에서, Person::getName 은 뭘까?
가끔 System.out::println 도 쓰던데.

Stream<String> stream
            = Stream.of("Geeks", "For",
                        "Geeks", "A",
                        "Computer",
                        "Portal");

stream.forEach(s -> System.out.println(s));
stream.forEach(System.out::println); //위에 코드와 같다. 



<Class name>::<method name>
해당 클래스 안에 메서드를 쓰겠다는 것.





---
3. why use Stream?


1. 람다쓰니까 간결, 깔끔해서
2. functional programming으로 도중에 데이터 변경할 걱정이 준다.
3. 병렬 실행 가능
	(ex. Stream().ParallelStream(), IntStream().parallel())
	
	  

*/
