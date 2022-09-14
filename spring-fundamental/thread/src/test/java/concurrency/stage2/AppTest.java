package concurrency.stage2;

import org.junit.jupiter.api.Test;

import java.net.http.HttpResponse;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;

class AppTest {

    /**
     * 1. App 클래스의 애플리케이션을 실행시켜 서버를 띄운다.
     * 2. 아래 테스트를 실행시킨다.
     * 3. AppTest가 아닌 App의 콘솔에서 SampleController가 생성한 http call count 로그를 확인한다.
     * 4. application.yml에서 설정값을 변경해보면서 어떤 차이점이 있는지 분석해본다.
     * - 로그가 찍힌 시간
     * - 스레드명(nio-8080-exec-x)으로 생성된 스레드 갯수를 파악
     * - http call count
     * - 테스트 결과값
     */

    /*
        ---
        what is this code?

        쓰레드 10개가 동시에 /test 로 GET 요청 보낸다.

        컨트롤러가 요청 받은 갯수 만큼, http call count: n을 출력한다.


        ---
        ???

        왜 http call count가 1,2 총 2개 밖에 안옴? (순서대로)

        ---
        Ans.

        server:
          tomcat:
            accept-count: 1
            max-connections: 1
            threads:
              max: 2

        application.yml에서 max thread가 2개여서 그렇구나.
        10개로 늘리면, http call count가 10개 오겠지?

        server:
          tomcat:
            accept-count: 1
            max-connections: 1
            threads:
              max: 10

        -> 응 아니야~ 똑같이 2개만 와~

        왜지?

        server:
          tomcat:
            accept-count: 3
            max-connections: 1
            threads:
              max: 10

        이렇게 해봤는데, 이번엔 4개 순서대로 옴. 응?


        server:
          tomcat:
            accept-count: 1
            max-connections: 5
            threads:
              max: 10
        이번엔 이렇게 해봤는데,

        이번엔 6개가 오는데, 순서대로 안옴. 3->2->1->4->5->6

        ???

        server:
          tomcat:
            accept-count: 3
            max-connections: 2
            threads:
              max: 10

        이렇게 해보면 1->2->3->4->5 순서대로 옴.
        다시 돌려보면, 1->2->4->3->5 이렇게 옴.
        근데 1->2 뚝 끊기고, 4->3 뚝 끊기고 5 이렇게 왔음.

        ???

        thread-max는 task queue에 넣는 thread의 총 갯수 인 것 같고,
        queue에서 부터 꺼내서 thread pool에 집어넣어서 쓰는 갯수는 accept-count + max-connections 인가?

        thread pool에서 한번에 병렬로 돌리는게 max-connections인 것 같다.
        그리고 max-connections * n번을 하는데, 그 n번이 accept-count 인 것 같다.

        근데 그럼 왜 6개가 안오고 5개만 오는거지?


        ---
        Ans.

        server:
          tomcat:
            accept-count: 3 # 작업큐의 사이즈
            max-connections: 2 # 수신가능한 connection의 총 개수
            threads:
              max: 10 # 생성할 수 있는 thread의 총 개수


        1. 톰캣은 총 10개의 쓰레드 생성 가능. (thread max:10)
        2. 10개 쓰레드 만들어서 동시에 /test 로 http request 보냄.
        3. task queue로 10개의 thread를 넣으려는데, 작업 queue의 사이즈가 3개밖에 안되서 3개만 들어감.(accept-count:3)
        4. "/test"를 수신하는 컨트롤러는 task queue에서 2개를 꺼내서 메서드 실행함. 1->2 수신 완료.
        5. 이 때, task queue에서 2개가 빠졌으니까, 뒤에 대기하던 8개의 thread중 2개를 채워서, 다시 task queue는 3개가 됨.
        6. 이제 다시 task queue에서 2개를 tomcat의 thread pool로 빼서 병렬로 돌림.
        7. 그럼 또 다시 task queue가 1개가 되고 기다리는 놈들 5명이 있으니까, 순서대로 2명 채워서 다시 3개가 되어야 하는데, test()에서 thread.start(); 이후, Thread.sleep(50); 한 후, thread.join();(쓰레드 동작 완료) 함.
        8. 따라서 task queue가 시간 초과로 채워지지 못하고, 3th, 4th번쨰 애가 병렬로 처리된 다음(4->3)
        9. 나머지 task queue에 남은 한놈까지 처리해서 (1->2, 4->3, 5) 이렇게 마무리 되는 것


        ---
        근데 AtomicInteger은 왜 쓴거야?

        race condition(concurrent problem) 해결하기 위한 방법 3가지 있는데,
        1. synchrnoized
        2. volatile
        3. Atomic Class

        멀티 쓰레드 환경에서 여러 쓰레드가 동시에 count라는 변수를 write하려니까(count++), 동시성 보장하려고 쓴 것.

     */

    private static final AtomicInteger count = new AtomicInteger(0);

    @Test
    void test() throws Exception {
        final var NUMBER_OF_THREAD = 10;
        var threads = new Thread[NUMBER_OF_THREAD];

        for (int i = 0; i < NUMBER_OF_THREAD; i++) {
            threads[i] = new Thread(() -> incrementIfOk(TestHttpUtils.send("/test")));
        }

        for (final var thread : threads) {
            thread.start();
            Thread.sleep(50);
        }

        for (final var thread : threads) {
            thread.join();
        }

        assertThat("count is 2", count.intValue() == 2);
    }

    private static void incrementIfOk(final HttpResponse<String> response) {
        if (response.statusCode() == 200) {
            count.incrementAndGet();
        }
    }
}