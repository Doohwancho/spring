package thread.level1_threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 스레드 풀은 무엇이고 어떻게 동작할까?
 * 테스트를 통과시키고 왜 해당 결과가 나왔는지 생각해보자.
 *
 * Thread Pools
 * https://docs.oracle.com/javase/tutorial/essential/concurrency/pools.html
 *
 * Introduction to Thread Pools in Java
 * https://www.baeldung.com/thread-pool-java-and-guava
 */
class ThreadPoolsTest {
	
	public static void main(String[] args) throws InterruptedException {
		testNewFixedThreadPool();
		System.out.println("-----------------");
		testNewCachedThreadPool();
    }

    static void testNewFixedThreadPool() {
        final var executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2); //thread 3개가 들어가도, 쓰레드 풀에서 병렬로 처리하는 최대 쓰레드의 양은 2개다. 3번째 쓰레드는 queue에서 기다리고 있다.
        executor.submit(logWithSleep("hello fixed thread pools1")); //테스트가 끝났지만, 로그가 출력이 안된다. 1초 뒤 미래에 출력되고 threadpool이 닫힌다.
        executor.submit(logWithSleep("hello fixed thread pools2"));
        executor.submit(logWithSleep("hello fixed thread pools3"));

        // 올바른 값으로 바꿔서 테스트를 통과시키자.
        final int expectedPoolSize = 2;
        final int expectedQueueSize = 1;

        System.out.println(executor.getPoolSize() == expectedPoolSize); //Executors.newFixedThreadPool(2); 했으니까 쓰레드 풀에 쓰레드 3개 넣어도 병렬로 작동하는 쓰레드 최대 갯수는 2개
        System.out.println(executor.getQueue().size() == expectedQueueSize); //쓰레드 풀에서 최대로 2개 도니까, 나머지 1개는 task Queue에서 대기 중이니까 1개 
    }

    static void testNewCachedThreadPool() {
        final var executor = (ThreadPoolExecutor) Executors.newCachedThreadPool(); //newCachedThreadPool는 쓰레드풀에서 concurrent하기 parallel하게 도는 제한 쓰레드 양이 없음. 3개 넣으면 3개 다 병렬로 돌아감
        executor.submit(logWithSleep("hello cached thread pools"));
        executor.submit(logWithSleep("hello cached thread pools"));
        executor.submit(logWithSleep("hello cached thread pools"));

        // 올바른 값으로 바꿔서 테스트를 통과시키자.
        final int expectedPoolSize = 3;
        final int expectedQueueSize = 0;

        System.out.println(executor.getPoolSize() == expectedPoolSize); //newCachedThreadPool는 동시에 병렬로 도는 쓰레드 제한이 없어서 3개 넣으면 3개 돌고, 10개 넣으면 10개 동시에 돔
        System.out.println(executor.getQueue().size() == expectedQueueSize); // 쓰레드풀에 넣은만큼 다 병렬로 도니까, task queue에서 기다리는 쓰레드가 없음. 그래서 0개
        
    }

    private static Runnable logWithSleep(final String message) {
        return () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(message);
        };
    }

    /*
    ---
    what is thread pool?

    When we use a thread pool, we write our concurrent code in the form of parallel tasks
    and submit them for execution to an instance of a thread pool.


    ---
    thread pool의 구조
    task을 할당할 땐, 하나의 task queue에 담아서, linear하게 threadpool에게 할당하되,
    threadpool에서는 병렬적으로 코드가 돈다.

    import java.util.concurrent.Executors;
    만 봐도, concurrent가 눈에 띈다.

	---
	diff thread.basic.ThreadTest thread.threadpool.ThreadPoolsTest
	
	그냥 Thread를 개별로 돌리면, .join()때문에 A쓰레드가 다 끝나는걸 확인한 이후, B 쓰레드를실행했는데,
	쓰레드풀 A,B를 순차실행해도, 각 쓰레드 풀에 있는 쓰레드의 갯수 총 6개가 동시 실행되면서, 결과값이 저렇게 뒤죽박죽 섞여서 나온다.
	
	hello cached thread pools
	hello fixed thread pools1
	hello fixed thread pools2
	hello cached thread pools
	hello cached thread pools
	hello fixed thread pools3

    ---
    thread pool의 파라미터 3개

    1. corePoolSize - number of core threads that will be instantiated and kept in the pool.
    2. maximumPoolSize - When a new task comes in, if all core threads are busy and the internal queue is full, the pool is allowed to grow up to maximumPoolSize.
    3. keepAliveTime - interval of time for which the excessive threads (instantiated in excess of the corePoolSize) are allowed to exist in the idle state.

    optimization할 때 쓰임


    ---
    Executors.newFixedThreadPool() vs Executors.newCachedThreadPool()


    1. newFixedThreadPool
    = 쓰레드 받고 동시에 병렬로 돌리되, 최대 병렬로 돌릴 수 있는 쓰레드 갯수 제한이 있고, 일이 끝나면 바로 자원 반환함.

    create a threadpool that equal corePoolSize and maximumPoolSize parameter values and a zero keepAliveTime
    This means that the number of threads in this thread pool is always the same

    2. newCachedThreadPool
    = 쓰레드 몇개든 다 받아서 모두 병렬로 돌림. 그리고 일이 끝나도 바로 자원 반환 안하고 60초 뒤에도 안쓰면 반환함

    This method does not receive a number of threads at all.
    We set the corePoolSize to 0 and set the maximumPoolSize to Integer.MAX_VALUE.
    Finally, the keepAliveTime is 60 seconds:

    */
}
