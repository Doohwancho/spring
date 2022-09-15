package thread.level4_solution2_volatile;

/**
 * ---
 * what is this code?
 * 
 * 1. thread A가 실행. while var running is true, count++ forever. 
 * 2. thread B가 초 뒤에 실행. turn running to false.
 * 3. but thread A never stops.
 * 
 * 
 * 
 * ---
 * why?
 * 
 * threadA는 var running 참조 시, 자신의 CPU의 cache를 참조함.
 * threadB는 자신의 CPU cache에 var running을 false로 바꿨기 때문에, 서로 다른 메모리 주소를 참조함.
 * 
 * 프로세스 안에 여러 쓰레드가 공유자원(code, data, heap) 참조하는 줄 알았는데, 
 * 이걸 최적화 하려고, 각 쓰레드마다 고유한 캐시 메모리 넣어서, 따로 쓰는구나.
 * 근데 volatile을 해주면, 이런 최적화 하지 않고,
 * 두 쓰레드가 프로세스 안 공유자원(code, data, heap)을 공유하구나!
 * 
 * 
 * 
 * ---
 * volatile의 문제
 * 
 * 멀티 쓰레드 환경에서 잘못 썼다간 race condition 발생해서 성능 크게 저하될듯? 
 * 
 * 
 * ---
 * 그럼 volatile 언제 씀?
 * 
 * 멀티 쓰레드 환경에서, 하나의 쓰레드만 read & write 하고, 다른 thread들은 only read가 보장될 때 사용.
 * 
 * 만약 여러 thread가 write해야 하는 상황이면, synchronized 사용해서 원자성(atomic) 보장하자.
 * 
 */

public class VolatileTest {
	volatile boolean running = true; //volatile 붙이니까, threadB가 1초 뒤에 running을 false로 바꿔서 threadA가 멈췄다!

    public void test() {
        new Thread(()->{
                int count = 0;
                while (running) {
                    count++;
                }
                System.out.println("Thread 1 finished. Counted up to " + count);
            }
        ).start();
        
        new Thread(()-> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {
                }
                System.out.println("Thread 2 finishing");
                running = false;
            }
        ).start();
    }

    public static void main(String[] args) {
        new VolatileTest().test();
    }
}
