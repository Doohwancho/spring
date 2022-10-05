package functional.recursion;

public class HeadRecursion {
	public static void main(String[] args) {
		printUsingHeadRecursion(5); //recursion 도는 max limit
	}

	public static void printUsingHeadRecursion(int n) {
		if (n == 0)
			return;
		else
			printUsingHeadRecursion(n - 1);

		//main logic here
		System.out.println(n);
	}
}

/*

---
console.log

1
2
3
4
5


---
실행순서

일단 HeadRecursion 메서드만 
5->4->3->2->1->0-> return 
찍고,

뒤로 돌아가면서, (0->1->2->3->4->5)
메인로직 실행하는 방법


*/