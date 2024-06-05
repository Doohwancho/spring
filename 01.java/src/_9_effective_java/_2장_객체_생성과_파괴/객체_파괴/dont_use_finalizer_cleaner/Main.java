package _9_effective_java._2장_객체_생성과_파괴.객체_파괴.dont_use_finalizer_cleaner;

public class Main {
	public static void main(String[] args) {
		//Q. what is finalizer, cleaner?
		//자바에서 제공하는 객체 소멸자. 
		
		//Q. avoid using finalizer, cleaner! 
		//왜? 
		//1. 예측 불가능 
		//2. 위험함(동작중 발생한 예외가 무시됨 + 보안문제 일으킬 수 있음) 
		//3. 보통 객체 회수 역할은 try-with-resource, try-finally로 해결됨
		//4. 느림. try-with-resource로 gc가 하는 객체 회수가 50배 더 느리다.  
		
		
		//case1) instead, use AutoCloseable
		SampleResource resource = new SampleResource();
		try {
			resource.hello(); // 리소스 사용
		} finally {
			resource.close(); // 리소스를 사용하는 쪽에서 쓴 다음 반드시 정리. close() 호출
		}
		
		//case2) try-with-resource is better than try-catch-finally
		try (SampleResource resource2 = new SampleResource()) {
			resource2.hello(); // 리소스 사용
		}
	}
}
