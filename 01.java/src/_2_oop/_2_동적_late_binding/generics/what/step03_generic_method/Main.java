package _2_oop._2_동적_late_binding.generics.what.step03_generic_method;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
한줄 요약

클레스의 제네릭과 메서드의 제네릭은 따로 노는데,
메서드의 제네릭 쓰려면 리턴타입 앞에 <T>로 초기화 해주면 된다.

 */

public class Main {
	public static void main(String[] args) {
		Name<String> name = new Name<>();
		
		//case1) 클래스의 붙은 T == 메서드에 붙은 T 
		System.out.println(name.getClassGenericType("hello").getClass().getSimpleName());
//		System.out.println(name.getClassGenericType(123).getClass().getSimpleName()); //error! - class의 <T>는 String인데 메서드의 T는 integer이라 타입 에러. 해결하려면 generic method 사용하라. 
		
		
		//case2) 클래스에 붙은 T != 메서드에 붙은 T 
		//"generic method"
		name.<Integer>printClassName(1); //에러가 안난다! - java.lang.Integer
		name.printClassName(1); //위와 같다. 근데 <Integer> 생략 가능. 
		
		name.<Double>printClassName(1.2); //에러가 안난다! - java.lang.Double
		name.printClassName(1.2); //위와 같다. 근데 <Double> 생략 가능. 
		
		//Q. 왜 Name<T>는 String이고 메서드 넣을 때 Integer 넣었는데 에러가 안남? 
		//A. 클래스에 붙은 T랑 메서드에 붙은 T가 따로놀거든. generic method를 쓰면.  
		
		
		//case3) <S> T method(S s){}
		String result = name.takeSandReturnT(123);
		System.out.println(result);
		//<S>로 로컬 제네릭 타입 선언해서 파라미터엔 쓰지만, 리턴타입이 클래스 제네릭 타입인 경우도 있다.
		
		
		
		//case4) static method에 쌩 generic T 못씀. (generic method <T> 써야 함) 
		List<String> cities = new ArrayList<>();
		cities.add("Changwon");
		cities.add("Seoul");
		cities.add("Suwon");
		cities.add("Yongin");

		for (String city : firstThree(cities)) {
		    System.out.print(city.toUpperCase()+", "); //CHANGWON, SEOUL, SUWON
		}
		System.out.println();
		
		
		
		//case5) practice example - box
		Box<Integer> box1 = Util.<Integer>boxing(100); //타입을 직접 지정해서 호출 
		int intValue = box1.getT();
		
		Box<String> box2 = Util.boxing("암묵적 호출"); //타입을 컴파일러가 보고 암묵적으로 호출. 
		String stringValue = box2.getT();
		
		System.out.println("intValue: "+intValue+"  stringValue: "+stringValue); //intValue: 100  stringValue: 암묵적 호출
		
		
		
		
		//case6)  List.sort() -> Comparator -> Arrays.sort() 뜯어보기. 
		
		//Q. 얜 뭔뜻?
		//public static <T extends Comparable<? super T>> void sort(List<T> list) 

		//A. 
		//1. 일단 void return type 앞에 <T> 있으니 제네릭 메서드임.
		//2. <T>
			//-> T를 해당 메서드 한정 초기화 시키려고 함. 
		//3. <T extends Comparable>
//			-> T는 Comparable 클래스를 구현하거나 상속받은 자식 클래스여야한다.
		//4. <T extends Comparable<T>>
//			-> 근데 그 Comparable 클래스의 generic type은 T이다. 
		//5. <T extends Comparable<? super T>>
//			-> 그 Comparable 클래스의 타입 X는 T나 T의 부모들 밖에 될 수 없다.
		
		//예를들어, 아래와 같이 Integer, Double, float 타입이 Number 타입으로 짬뽕된 List를 sort()하고 싶다고 하자. 
		//sort()안에는 Comparable 클래스를 넣어줘야 그걸 보고 정렬하겠지?
		//내가 넣으려는 comp는 Comparator 클래스이다. (3번 조건 충족)
		//그 Comparator를 선언할 때, <Number>로 선언했는데, 저 Number가 generic type T다.  Comparator<T> == Comparator<Number> 
		//그리고 그게 4번 조건에 <T extends Comparable<T>>와 같다.  (<Number extends Comparable<Number>>인 셈.)  
		//T가 Number인데, 5번 조건에서 ? super T라는 말은, T가 Number과 같거나 Number의 부모들 타입이 되어야 한다는 말이다.
		//따라서 <Number extends Comparable<Number>>, <Number extends Comparable<Object>>가 가능하다.   
		
		List<Number> numberList = new ArrayList<>();
		numberList.add(10); //integer
		numberList.add(3.14f); //float
		numberList.add(1.2); //double
		
		//Number로 구현한 Comparator
//		Comparator<Number>comp = new Comparator<Number>() { //	<T extends Comparable<? super T>> 이니까, Comparable은 T인 Number이나 그 이상인 Object도 가능하다. 
//			@Override
//			public int compare(Number a, Number b) {
//				return a.intValue() - b.intValue(); //ASC sort 
//			}
//		};
		
		//Object로 구현한 Comparator 
		Comparator<Object>comp = new Comparator<Object>() {			
			@Override
			public int compare(Object a, Object b) {
				return ((Number)a).intValue() - ((Number)b).intValue(); //ASC sort 
			}
		};
		
		numberList.sort(comp); //<T extends Comparable<? super T>> void sort(List<T> list)
		
		System.out.println(numberList.toString()); //[1.2, 3.14, 10]

		
		//List.sort()를 뜯어보자.
		
		/*
		 List.sort()는 일단 이 안에 정의되어 있다.
		 
		 public interface List<E> extends Collection<E> {}
		 
		 List는 Collection과 같은 generic type을 공유한다. 
		 
		 
		    default void sort(Comparator<? super E> c) {
		        Object[] a = this.toArray();
		        Arrays.sort(a, (Comparator) c);
		        ListIterator<E> i = this.listIterator();
		        for (Object e : a) {
		            i.next();
		            i.set((E) e);
		        }
		    }
		 */
		//List<E>에서 E와 같거나 E의 부모 타입인 Comparator을 파라미터로 받는다. 
		
		//List에 있는 애들을 array 배열로 옮긴 후, sort()하는데, Arrays.sort()도 뜯어보자.
		
		/*
		    public static <T> void sort(T[] a, Comparator<? super T> c) {
		        if (c == null) {
		            sort(a);
		        } else {
		            if (LegacyMergeSort.userRequested)
		                legacyMergeSort(a, c);
		            else
		                TimSort.sort(a, 0, a.length, c, null, 0, 0);
		        }
		    }
		 */
		
		//<T>로 T를 초기화 하고, 그 타입의 T[] a 오브젝트 배열, Comparator<? super T> T 혹은 T의 부모가 담긴 Comparator를 받네.
		//그러니까 Number[] 배열을 정렬하고 싶을 때, Comparator<Number>도 받지만, Comparator<Object>도 받는다는 말이네. 
		//그래서 위에서 Comparator<Object>comp = new Comparator<Object>(){} 구현했던거고. 
		
		
		//근데 저 sort(a);는 무슨 정렬 쓸라나?
		/*
	    public static void sort(Object[] a) {
	        if (LegacyMergeSort.userRequested)
	            legacyMergeSort(a);
	        else
	            ComparableTimSort.sort(a, 0, a.length, null, 0, 0);
	    }
		*/
		
		//역시 O(log N) 갓-머지 소트 님이시다.  
		
	}
	
	
	//case4)
//	static T firstThree_fail(List<T> list) { //error! but why?
//		return list.stream().limit(3).collect(Collectors.toList());
//	}
	//static method에 쌩 generic T 못씀. (generic method 써야 함)  
	//왜냐하면 클래스가 인스턴스화 되기 전에 메모리에 올라가는데, 그 시점에 이 메서드에 타입 안정해졌잖아. 자바 static type language임. 
	

	//case4)
	static <T> List<T> firstThree(List<T> list) { 
	    return list.stream().limit(3).collect(Collectors.toList());
	}
	
}
