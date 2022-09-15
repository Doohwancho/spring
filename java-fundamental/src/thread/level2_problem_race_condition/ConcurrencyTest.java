package thread.level2_problem_race_condition;

/**
 * 	---
 * 	what is this code?
 *
 *	공유 자원 i가 있음.
 *	쓰레드 2개 돌림. 
 *	쓰레드 A는 i를 +1함. 
 *	쓰레드 B는 i를 -1함.
 *	
 *	결과: 난장판. i: -17095, i: 355811, i: 164100, etc.. 
 *
 *	
 *  
 *	---
 *	왜?
 *
 *	race condition 걸려서, 쓰레드 A,B가 순서 생각 안하고 공유자원 i를 참조하기 때문.
 *
 *
 *
 *	---
 *  멀티 쓰레드 환경에서 동시성 문제 어떻게 해결하지?
 *  
 *  
 *  ---	
 *	해결책1: 동기화(synchronization)
 *
 *	countUp(), countDown() 메서드에 synchronized 먹여주면, 놀랍게도 
 *	thread A,B 둘 다 사이좋게 countUp(), countDown()을 10만번씩 돌고 i는 0이 된다. 
 *
 *	안전하게 동시성을 보장할 수 있다. 
 *	하지만 solution 3가지 방법 중, 비용이 가장 크다.
 *
 *	---
 *	해결책2: volatile
 *
 *	synchronized보다 비용이 적지만, thread1은 쓰기만 하고, 나머지 쓰레드들은 읽기만 하는게 보장된 경우에만 사용 가능하다.
 *
 *	---
 *	해결책3: Atomic Class 활용
 *
 *	CAS(compare-and-swap) 방식 이용하여 동시성 보장한다. 
 *	여러 쓰레드에서 데이터 write해도 문제 없다. 
 *
 *	synchronized보다 비용이 적다.
 *
 *	
 */

public class ConcurrencyTest {


    // Non-volatile integer "result".
    private int i;


    public static void main(String[] args) throws InterruptedException {
        new ConcurrencyTest();
    }

    public ConcurrencyTest() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                countUp();
            }
        }, "Thread-1");

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                countDown();
            }
        }, "Thread-2");

        t1.start();
        t2.start();

        // Wait for two threads to complete.
        t1.join();
        t2.join();

        // Print out result.  With correct concurrency control we expect the result to
        // be 0.  A non-zero result indicates incorrect use of concurrency.  Also note
        // that the result may vary between runs because of this.
        System.err.println("i: " + i);
    }

    private synchronized void countUp() {
        // Increment instance variable i 1000,000 times.  The variable is not marked
        // as volatile, nor is it accessed within a synchronized block and hence
        // there is no guarantee that the value of i will be reconciled back to main
        // memory following the increment.
        for (int j=0; j<1000000; ++j) {
            ++i;
        }
    }

    private synchronized void countDown() {
        // Decrement instance variable i 1000,000 times.  Same consistency problems
        // as mentioned above.
        for (int j=0; j<1000000; ++j) {
            --i;
        }
    }
}
