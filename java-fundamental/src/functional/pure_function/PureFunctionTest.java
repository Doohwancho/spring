package functional.pure_function;

public class PureFunctionTest {
	private String name = "무명소졸"; 

	//Not Pure 
	public String greeting() { 
		return "Hello " + name; 
	} 

	//Pure function 
	public static String greeting(String name) { 
		return "Hello" + name; 
	}
}

/*

---
why?

파라미터에 받은거 고대로 나가니까. 
중간에 저 name에 조작이 없으니까.


input -> computation -> output에서,

computation에서 뭔짓이 있던, input 넣은게 output으로 고대로 나와서 예측 가능해야 한다.
-> 예측 가능성 높임
-> 안정성 증가 


*/