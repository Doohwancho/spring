package thread.level0_basic_thread;

public class WaitAndNotifyTest {

	public static void main(String[] args) {
		ThreadA a = new ThreadA();
		a.start();
		
		synchronized(a) {
			try {
				System.out.println("a가 완료할 때 까지 대기");
				a.wait();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("total is: "+ a.total);
		}
	}
}


/*

---
what is this code?


wait()하면 잠들었다가, 
Runnable state한 애가 일 끝나고 notify()해서 잠자던애 깨우는 코드.



원래 main()에서 쓰레드 실행하면,
메인 쓰레드 끝나도 쓰레드 돌잖아?
근데 가끔 쓰레드 다 끝날 때 까지 main() 끝내고 싶지 않은 경우가 있어.
물론 join()으로도 해결 가능하지만,
wait() && notify()로 해결도 가능.

이 예제 말고 실전에서 모니터 같은애 뜯어볼 때 종종 나올 듯 





---
console.log

a가 완료할 때 까지 대기
0 를 출력합니
1 를 출력합니
2 를 출력합니
3 를 출력합니
4 를 출력합니
total is: 10

*/