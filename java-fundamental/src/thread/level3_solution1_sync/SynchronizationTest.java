package thread.level3_solution1_sync;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

class SynchronizationTest {

    /**
     * 테스트가 성공하도록 SynchronizedMethods 클래스에 동기화를 적용해보자.
     * synchronized 키워드에 대하여 찾아보고 적용하면 된다.
     *
     * Guide to the Synchronized Keyword in Java
     * https://www.baeldung.com/java-synchronized
     */
	public static void main(String[] args) throws InterruptedException {
		testSynchronized();
    }
	
    static void testSynchronized() throws InterruptedException {
        var executorService = Executors.newFixedThreadPool(3);
        var synchronizedMethods = new SynchronizedMethods();

        IntStream.range(0, 10000)
                .forEach(count -> executorService.submit(synchronizedMethods::calculate));
        executorService.awaitTermination(500, TimeUnit.MILLISECONDS);

        System.out.println(synchronizedMethods.getSum());
        System.out.println(synchronizedMethods.getSum() == 10000); //fail: expected 1000, but was 970
        //100개만 돌리면 된다. 근데 1000개 돌리면 970개만 된다. 약간 모자름. 10000개 돌려도 9942로 약간 모자름. 왜지?
        //executorService.awaitTermination(500, TimeUnit.MILLISECONDS); 때문인 듯?
        //500 millisecond 이후에 쓰레드풀을 terminate 시키니까, 나머지 못채우고 쓰레드풀을 닫아서 생기는 문제인 듯?

        //문제의 원인은 race condition.
        //해결하기 위해 synchronized를 쓰자.
    }

    private static final class SynchronizedMethods {

        private int sum = 0;

        //여기에 synchronized를 붙이는게 정답!. 
        //synchronized가 있으면 다중 쓰레드 환경에서도 한번에 한 쓰레드 밖에 접근할 수 없다.
        //동기화란, Critical Section(임계 영역)을 정해주는 것 
        public synchronized void calculate() { 
            setSum(getSum() + 1);
        }

        public int getSum() {
            return sum;
        }

        public void setSum(int sum) {
            this.sum = sum;
        }
    }

    /*

    ---
    what is synchronization?

    멀티 쓰레드 쓰는 환경에서, 같은 메모리 동시참조하려할 때, race condition 발생.

    synchronized 세팅 해 두면, 저 공유자원에 한번에 한 thread만 접근하도록 제한하는 것


    ---
    curious: race condition인데 왜 느린거야?

    race condition은 greedy algorithm처럼 기냥 몰려드는건데,
    왜 순서대로 한번에 한놈씩만 접근하라는 애 보다 느린걸까?

    어짜피 순서대로 화장실 들어가나, 치고박고 싸우며 화장실 들어가나,
    왔다갔다 나오는 시간은 결과적으론 똑같을거아냐?


    A. 예시1
    철수가 냉장고에 내가 먹고싶은 음식 킵해놨는데, 영희가 냉장고에 음식 꺼내간 경우, 철수는 냉장고 앞에서 바보처럼 기다리고 있어야 함.
    그래서 race condition이 느린 것.

    A. 예시2
    1. 철수랑 영희 둘 다 냉장고에 계란이 필요함. 요리할라고.
    2. 철수가 먼저 냉장고 열어서 계란 가져감.
    3. 근데 계란 쓰고 바로 돌려놓지 않고, 다른 요리(스파게티, 스테이크 등..) 이 끝날 때 까지 안돌려줌
    4. 영희는 냉장고 앞에서 계란 언제오나 기다려야 함
    5. 전체 프로세스가 느려짐
    6. 그러니까 sync 걸어서 공유자원하는 애는 한번에 한놈만 접근 가능하고, 다 쓰면 바로 돌려놓기로 약속함.

     */
}

