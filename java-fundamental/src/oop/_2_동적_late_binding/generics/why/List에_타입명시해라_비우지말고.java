package oop._2_동적_late_binding.generics.why;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class List에_타입명시해라_비우지말고 {
	/* item 26. raw type 쓰지 말고 generic type 명시하자! */
	
	public static void main(String[] args) {
		//case1) raw type이니 아무거나 막들어간다. 런타임때 문제일으킬 생각하니 벌써부터 머리가.. 
		List list = new ArrayList<>();
		list.add(123);
		list.add("hello");
		list.add(3.14f);
		
		//아무거나 막넣을거라면 제발 List<Object>라고 명시해라!
		//List는 제네릭 타입에서 완전히 손 놓은건데, List<Object>는 컴파일러한테 적어도 명확히 모든걸 넣겠다는 의도를 전달한거니까. 
		
		//raw type을 열어놓은건 자바가 동적 타입 언어라서 그런게 아니라, 옛날에 제네릭 없었을 시절 코드까지 호환해야 하기 때문에 어쩔 수 없이 열어놓은거다.. 
		
		//case2) 
		List<String> strings = new ArrayList<>();
		unsafeAdd(strings, Integer.valueOf(10));
		String s = strings.get(0); //compile땐 멀쩡하다가 runtime error! -> java.lang.ClassCastException.
	}
	
	private static void unsafeAdd(List list, Object o) { //raw type인 List로 받으니까, String List에 Integer가 더해지는 참사가 난다... 
		//List -> List<Object> 하면 컴파일 에러난다. List<String> != List<Object> 이기 때문. 
		list.add(o);
	}
	
	
	//case3) 
	static void setMethod(Set s1, Set s2) { //이거 역시 raw type으로 그냥 넘어가네? 
		//Set에 뭐가 들어가는지 안정해졌으면, Set<?>이라도 명시해 두는게 더 좋다. 
	}
}
