package thread.level2_concurrency;

/**
 * 	what is this code?
 *
 *	공유 자원 i가 있음.
 *	쓰레드 2개 돌림. 
 *	쓰레드 A는 i를 +1함. 
 *	쓰레드 B는 i를 -1함.
 *	
 *	결과: 난장판. i: -17095, i: 355811, i: 164100, etc.. 
 *	
 *	왜?
 *
 *	race condition 걸려서, 쓰레드 A,B가 순서 생각 안하고 공유자원 i를 참조하기 때문.
 *
 *	
 *	해결책: 동기화(synchronization)
 *
 *	countUp(), countDown() 메서드에 synchronized 먹여주면, 놀랍게도 
 *	thread A,B 둘 다 사이좋게 countUp(), countDown()을 10만번씩 돌고 i는 0이 된다. 
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
