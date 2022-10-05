package functional.theory.recursion;

public class TailRecursion {
	public static void main(String[] args) {
		printUsingTailRecursion(5); //recursion 도는 max limit 
	}

	public static void printUsingTailRecursion(int n) {
		if (n == 0)
			return;
		else
			//main logic here
			System.out.println(n); 
		printUsingTailRecursion(n - 1);
	}
}
/*


---
console.log

5
4
3
2
1


---
실행순서

(5, 메인로직1) -> (4, 메인로직2) -> (3, 메인로직3) -> (2, 메인로직4) -> (1, 메인로직5) -> (0, return) 

이후 쭈루룩 return하다 끝남.




*/