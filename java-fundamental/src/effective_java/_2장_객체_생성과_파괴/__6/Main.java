package effective_java._2장_객체_생성과_파괴.__6;

import java.util.regex.Pattern;

public class Main {
	/** 객체 생성할 떄, 불필요한 부분 최소화하기! */
	public static void main(String[] args) {
		
		//case1) new String(), 2L 반복해서 쓰는거 조심하자.
		String s1 = new String("java"); //이거 100번해서 String 객체 100개만드는것 보다, 
		String s2 = "Java"; //이렇게 선언하면 100개 인스턴스가 저 "Java" 하나만 참조한다. -> 메모리 절약 
		
		//case2) static factory method 사용하기 
		Boolean true1 = Boolean.valueOf("true");
		Boolean true2 = Boolean.valueOf("true");

		System.out.println(true1 == true2); // true. true1,2,3,4,5,.. 몇개를 만들던, n개의 인스턴스는 모두 저 "true"하나만을 가르킴. 
		
		
	}
	
	//case3) 무거운 객체 처리
    private static final Pattern ROMAN = Pattern.compile(
            "^(?=.)M*(C[MD]|D?C{0,3})"
                    + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$"); //미리 regex 패턴을 컴파일해놓고, static에 올려놓은 다음,  

    static boolean isRomanNumeralFast(String s) {
        return ROMAN.matcher(s).matches(); //두고두고 사용. 왜? 매번 저 regex Pattern initialize하는데 비용, 시간 들거든. '캐싱'해놓는 것. 
    }
    
    
    
    //case4) auto-boxing 주의
    private static long sum() {
    	Long sum = 0L; //내부적으로 new Long();이 만들어지는 중. 이걸 wrapper class->primitive type으로 바꾸고 연산하면 훨씬 빨라짐. 
    	for(long i=0; i<=Integer.MAX_VALUE; i++) {
    		sum += i;
    	}
    	return sum;
    }
}
