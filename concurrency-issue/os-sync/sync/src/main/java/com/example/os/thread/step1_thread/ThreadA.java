package main.java.com.example.os.thread.step1_thread;

public class ThreadA extends Thread{
	int total;
	
	public void run() {
		synchronized(this) {
			for(int i = 0; i < 5; i++) {
				System.out.println(i+" 를 출력합니다");
				
				total += i;
				
				try {
					Thread.sleep(500);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			notify(); //작업 완료 후, WaitAndNotifyTest에 main()이 wait()로 자고있던걸 notify()로 꺠워줌. 
		}
	}
}
