package prototype;

public class Main {
	public static void main(String[] args) {
		final String TEST = "TEST";

	    MessageBox starBox = new MessageBox("*");

	    PrototypeService prototypeService = new PrototypeService();
	    
	    prototypeService.register("starBox", starBox);
        
        Product copy = prototypeService.create("starBox");
        
        System.out.println(copy.use(TEST).equals("*TEST*")); //true
        System.out.println(copy.getClass().toString().equals(starBox.getClass().toString())); //true - 기존 객체 이름 == 기존 객체에서 파생된 새 객체 이름 
        System.out.println(copy.hashCode() == starBox.hashCode()); //false - 기존 객체 hash != 기존 객체에서 파생된 새 객체 hash. 왜? deep copy from 'extends Cloneable'
        System.out.println(copy.maps.hashCode() == starBox.maps.hashCode()); //true. but why????  
	}
}

/*

기존 object 복사(clone)


잠깐!!! 주의사항

extends Cloneable의 clone();이 deep copy라면,
System.out.println(copy.maps.hashCode() == starBox.maps.hashCode()); 가 true인게 말이 안됨.

원본 객체의 불변성이 보장되어야 하잖아..
clone으로 복사본 여러개 만들고, 원본객체의 map 바꾸면, 복사본의 값들까지 일괄 바뀜.

maps의 clone을 개발자가 따로 구현해 주어야 진정한 의미의 deep copy가 됨.

*/