package __2.객체_생성과_파괴.__1;

import java.math.BigInteger;
import java.time.LocalTime;
import java.util.Random;

import __2.객체_생성과_파괴.__1.User;
import __2.객체_생성과_파괴.__1.level.Level;

public class Main {

	/*
	 * 정적 펙토리 메서드 네이밍 컨벤션 
	 * 
		from : 하나의 매개 변수를 받아서 객체를 생성
		of : 여러개의 매개 변수를 받아서 객체를 생성
		getInstance | instance : 인스턴스를 생성. 이전에 반환했던 것과 같을 수 있음.
		newInstance | create : 새로운 인스턴스를 생성
		get[OtherType] : 다른 타입의 인스턴스를 생성. 이전에 반환했던 것과 같을 수 있음.
		new[OtherType] : 다른 타입의 새로운 인스턴스를 생성.
	 */
	
	public static void main(String[] args) {
		//case1) java.util은 다 static factory method 패턴으로 구현함. 45개 util 클래스 싹다 외부 공개 안해도 되니까 api 문서 간략화 + 기존 구현클래스 어떻게 바꿀지 고민 안해도 됨.  
		LocalTime.of(10, 30);
		
		
		//case2) enum
		Color redColor = Color.valueOf("RED");
		Color blueColor = Color.valueOf("BLUE");
		
		
		//case3) 생성자가 이름을 가진다 -> 명확화 
		User gildong = User.KoreanUser("gildong",20); //유저 중에서도 한국 유저 
		BigInteger bigInteger = BigInteger.probablePrime(1, new Random()); //BigInteger 중에서도 소수
		
		
		//case4) 호출 될 떄마다 인스턴스 새로 생성 안함. -> singleton 
		User instance = User.getInstance();
		
		
		//case4) 호출 될 떄마다 인스턴스 새로 생성 안함. 
		LottoNumber lotto = LottoNumber.of(30); //스프링에서 RequestMapper가 컨트롤러 빈 관리할 때 이렇게 하던데 
		
		
		//case5) 자유로운 형변환 가능 
		Car car = new Car();
		CarDto carDto = CarDto.from(car); // 정적 팩토리 메서드를 쓴 경우
//		CarDto carDto = new CarDto(car.getName(), car.getPosition); // 생성자를 쓴 경우. 내부 구현 다 드러내야 함 -> 코드 지저분 
		
		
		//case6) 하위 자료형 객체 반환 가능 + 생성자에 로직 추가 가능. 
		Level level = Level.of(70); //Basic || Intermediate || Advance 반환 
	}
}


/*

static method 의 단점: public, protected 생성자가 없어서 상속 불가. 자식 못만듬.

*/