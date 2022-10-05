package functional.applied_examples.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;



public class Main {
	public static void main(String[] args) {
		//case)1
		String name;
		Person minsu = new Person("minsu");
		
		if(minsu.getName() != null) {
			name = minsu.getName();
		} else {
			name = "hanna";
		}
		System.out.println(name);
	
		
		//case2)
		name = Optional.of(minsu).map(Person::getName).orElse("hanna");
		System.out.println(name);
		
		
		
		//example1) Optional로 ArrayList초기화
		List<String> nameList = Optional.ofNullable(getNames()).orElseGet(() -> new ArrayList<>());
		
		
		
		//example2) 객체 null검사 여러번을 축약 가능 
//		public String findPostCode() {
//		    UserVO userVO = getUser();
//		    if (userVO != null) {
//		        Address address = user.getAddress();
//		        if (address != null) {
//		            String postCode = address.getPostCode();
//		            if (postCode != null) {
//		                return postCode;
//		            }
//		        }
//		    }
//		    return "우편번호 없음";
//		}
		
		//저렇게 긴걸 한줄로 줄이기 가능 
//		String result = user.map(UserVO::getAddress).map(Address::getPostCode).orElse("우편번호 없음");
		
		
		//exmaple3) Exception 처리
		
//		String name = getName();
//		String result = "";
//
//		try {
//		    result = name.toUpperCase();
//		} catch (NullPointerException e) {
//		    throw new CustomUpperCaseException();
//		}
		
		
		//저리 긴걸 두줄 축약 가능 
//		Optional<String> nameOpt = Optional.ofNullable(getName());
//		String result = nameOpt.orElseThrow(CustomUpperCaseExcpetion::new)
//		                  .toUpperCase();
		
		
		
	}
	
	//example1)
	static List<String> getNames(){
		List<String> list = new ArrayList<>();
		list.add("minsu");
		list.add("hanna");
		return list;
	}
	
	

	
    
            
}

/*

---
why use optional?


sophisticated way of handling 'null' in java


Optional == null까지 감싸는 wrapper class


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