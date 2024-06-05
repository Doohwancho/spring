package main.java.com.example.os.solutions.step3_semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	/*
	 * ---
	 * what is semaphore?
	 * 
	 * 
	 * 
	 * mutex나 semaphore나 critical section 구현 사례.
	 * 
	 * 
	 * 근데 mutex는 화장실이 딱 한칸밖에 없어서, 10명이 오면 젤 먼저온 한놈만 들어갈 수 있다면, 
	 * semaphore은 화장실이 3칸이라, 10명 오면, 선착순 3명씩 들어갈 수 있다. 
	 * 
	 *  
	 * 
	 * new Semaphore(3); 으로 3개로 제한하면,  
	 * 
	 * 실행 결과 
	 *  [Thread-2]1쓰레드가 점유중 //2번 쓰레드가 사용 중 
		[Thread-1]2쓰레드가 점유중 //2번, 1번 쓰레드가 사용 중  
		[Thread-0]3쓰레드가 점유중 //2번, 1번, 0번 쓰레드가 사용 중 
		[Thread-3]3쓰레드가 점유중 
		[Thread-4]3쓰레드가 점유중 //... 이후 쭉 3개 쓰레드만 점유 중이다. 그 이상 늘어나지 않는다. 
		[Thread-6]3쓰레드가 점유중
		[Thread-5]3쓰레드가 점유중
		[Thread-8]3쓰레드가 점유중
		[Thread-9]3쓰레드가 점유중
		[Thread-7]3쓰레드가 점유중

	 * 
	 * 
	 * 
	 * 
	 * 
	 * ---
	 * thread pool이랑 똑같은거 아님? 그럼 걍 thread pool 쓰지 왜 semaphore씀?
	 * 
	 * 둘 다 한번에 몇개 쓰레드 동시에 실행시킬지 정해주는데,
	 * semaphore은 한층 더 진화해서, 
	 * 쓰레드가 동시에 수행할 수 있는 영역을 지정해 준다.
	 * 
	 * semaphore.acquire();
	 * 
	 * //... 이 영역 안에 명령어 추가로 집어넣을 수 있음. 
	 * 
	 * semaphore.release();
	 * 
	 * 
	 * semaphore = thread pool + AOP
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * ---
	 * semaphore 단점
	 * 
	 * 
	 * hold and wait 전략(.wait(), .notify()) 로 공유자원에 대한 접근을 제한함. 
	 * 근데 .wait()이랑 .notify() 서순이 어긋나거나 둘 중 하나라도 생략되면, 바로 데드락걸림. 
	 * 이걸 정교하게 컨트롤하기 어렵기 때문에 모니터가 나옴. 
	 *  
	 */
    

	public static void main(String[] args) {

  		final SomeResource resource = new SomeResource(3);

  		for(int i = 1 ; i <= 10 ; i++) {
  			Thread t = new Thread(new Runnable() {
  				public void run() {
  					resource.use();
  				}
  			});
  			t.start();
		}
  	}
  	

  	
	static class SomeResource {
      	private final Semaphore semaphore;
      	private final int maxThread;
    
    
      	public SomeResource(int maxThread) {
      		this.maxThread = maxThread;
      		this.semaphore = new Semaphore(maxThread); //여기서 maxThread 갯수 제한 거는 듯? 
      	}
      	public void use() {
      		try {
      			semaphore.acquire(); // Thread 가 semaphore에게 시작을 알림
      			System.out.println("[" + Thread.currentThread().getName() + "]"
      											+ (maxThread - semaphore.availablePermits()) + "쓰레드가 점유중" );
      			// semaphore.availablePermits() 사용가능한 Thread의 숫자
      			Thread.sleep((long) (Math.random() * 10000));
      			semaphore.release(); // Thread 가 semaphore에게 종료를 알림
      		} catch (InterruptedException e) {
      			e.printStackTrace();
      		}
      	}
      }
}
