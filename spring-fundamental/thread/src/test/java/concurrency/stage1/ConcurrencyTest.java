package concurrency.stage1;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import stage1.HttpProcessor;
import stage1.User;
import stage1.UserServlet;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * 스레드를 다룰 때 어떤 상황을 조심해야 할까?
 * - 상태를 가진 한 객체를 여러 스레드에서 동시에 접근할 경우
 * - static 변수를 가진 객체를 여러 스레드에서 동시에 접근할 경우
 *
 * 위 경우는 동기화(synchronization)를 적용시키거나 객체가 상태를 갖지 않도록 한다.
 * 객체를 불변 객체로 만드는 방법도 있다.
 *
 * 웹서버는 여러 사용자가 동시에 접속을 시도하기 때문에 동시성 이슈가 생길 수 있다.
 * 어떤 사례가 있는지 아래 테스트 코드를 통해 알아보자.
 */

public class ConcurrencyTest {
    @Test
    void test() throws InterruptedException {
        final var userServlet = new UserServlet();

        // 웹서버로 동시에 2명의 유저가 gugu라는 이름으로 가입을 시도했다.
        // UserServlet의 users에 이미 가입된 회원이 있으면 중복 가입할 수 없도록 코드를 작성했다.
        final var firstThread = new Thread(new HttpProcessor(new User("gugu"), userServlet));
        final var secondThread = new Thread(new HttpProcessor(new User("gugu"), userServlet));

        // 스레드는 실행 순서가 정해져 있지 않다.
        // firstThread보다 늦게 시작한 secondThread가 먼저 실행될 수도 있다.
        firstThread.start();
        secondThread.start();
        secondThread.join(); // secondThread가 먼저 gugu로 가입했다.
        firstThread.join();

        // 이미 gugu로 가입한 사용자가 있어서 UserServlet.join() 메서드의 if절 조건은 false가 되고 크기는 1이다.
        // 하지만 디버거로 개별 스레드를 일시 중지하면 if절 조건이 true가 되고 크기가 2가 된다. 왜 그럴까?
        System.out.println(userServlet.getUsers().size());
        assertThat("user servlet has size of one", userServlet.getUsers().size() == 1);
    }

    /*

    Q. what is the problem?

    create inconsistent results due to lack of concurrency control between two threads.

    A. How to solve them?

     */

    public static class concurrencyTest {
        private static final Logger log = LoggerFactory.getLogger(concurrencyTest.class);

        // Non-volatile integer "result".
        private int i;

        @Test
        public void main(String[] args) throws InterruptedException {
            System.out.print("@#$@#$@#$@#$@#$@#$");
            log.info("!@#!@#!#$@#%@#%@#%");
            new concurrencyTest();
        }

        public concurrencyTest() throws InterruptedException {
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

        private void countUp() {
            // Increment instance variable i 1000,000 times.  The variable is not marked
            // as volatile, nor is it accessed within a synchronized block and hence
            // there is no guarantee that the value of i will be reconciled back to main
            // memory following the increment.
            for (int j=0; j<1000000; ++j) {
                ++i;
            }
        }

        private void countDown() {
            // Decrement instance variable i 1000,000 times.  Same consistency problems
            // as mentioned above.
            for (int j=0; j<1000000; ++j) {
                --i;
            }
        }
    }
}
