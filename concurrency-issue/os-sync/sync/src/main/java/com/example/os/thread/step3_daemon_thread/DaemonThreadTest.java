package main.java.com.example.os.thread.step3_daemon_thread;

public class DaemonThreadTest implements Runnable{
	
	
	@Override
	public void run() {
		while(true) {
			System.out.println("데몬 쓰레드 실행 중!");
			try {
				Thread.sleep(50);
			} catch(InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
		
	}
	
	public static void main(String[] args) {
		Thread thread = new Thread(new DaemonThreadTest());
		thread.setDaemon(true);
		thread.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("메인 쓰레드 종료!");
	}
}

/*

---
what is daemon thread?



백그라운드에서 돌아가는 쓰레드

윈도우엔 Service
Linux엔 Daemon



데몬 쓰레드 특징: 
일반쓰레드가 모두 종료되면, 강제종료된다. 


즉, main()이 종료되면, 강제종료된다. 


*/
