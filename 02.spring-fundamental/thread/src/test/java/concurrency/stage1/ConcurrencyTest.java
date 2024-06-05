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
        System.out.println(userServlet.getUsers().size()); //유저가 2개가 아닌 1개인 이유는, UserServlet에 .join()에서 유저가 중복되면 add하지 말라는 명령어가 있기 때문.
        assertThat("user servlet has size of one", userServlet.getUsers().size() == 1); //어쩔땐 1이었다가, 2었다가.. 근데 synchronized 쓰면 항상 1!
        //TODO - e-2. 두 스레드가 동시에 접근할 경우 어떤 문제가 발생할 수 있을까?
    }

    /*

    Q. what is the problem?

    create inconsistent results due to lack of concurrency control between two threads.

    A. How to solve them?

     */
}
