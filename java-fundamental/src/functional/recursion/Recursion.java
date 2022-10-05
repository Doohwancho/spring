package functional.recursion;

public class Recursion {
	public static void main(String[] args) {
		
		//case1) imperative method 
		System.out.println("Sum using imperative way. Sum(5) : " + sum(5));
		
		
		//case2) recursion
		System.out.println("Sum using recursive way. Sum(5) : " + sumRecursive(5));
	}

	private static int sum(int n) {
		int result = 0;
		for (int i = 1; i <= n; i++) {
			result = result + i;
		}
		return result;
	}

	private static int sumRecursive(int n) {
		if (n == 1) {
			return 1;
		} else {
			return n + sumRecursive(n - 1);
		}
	}
}

/*

recursion이 imperative 방법보다 더 간략하고, 함수를 '재사용'한다는 측면에서 좋으나,
실제 성능은 그닥..(함수스택 여러번 쌓이는게 별로 안좋을거야.)


recursion쓸 때, recursion안에 로직이 발동되는 순서를 
1. tail recursion
2. head recursion 

으로 조절할 수 있다.

최대 recursion이 총 몇번까지 돌아야 하는지 정해줄 수 도 있다. (5번 이상 돌면 성능이 그닥...) 


*/