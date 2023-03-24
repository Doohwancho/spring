package main.java.com.example.thread.step1_thread;


import java.util.Random;



/**
 * 자바로 동시에 여러 작업을 처리할 때 스레드를 사용한다.
 * 스레드 객체를 직접 생성하는 방법부터 알아보자.
 * 진행하면서 막히는 부분은 아래 링크를 참고해서 해결한다.
 *
 * Thread Objects
 * https://docs.oracle.com/javase/tutorial/essential/concurrency/threads.html
 *
 * Defining and Starting a Thread
 * https://docs.oracle.com/javase/tutorial/essential/concurrency/runthread.html
 */
class ThreadTest {

    /**
     * 자바에서 직접 스레드를 만드는 방법은 2가지가 있다.
     * 먼저 Thread 클래스를 상속해서 스레드로 만드는 방법을 살펴보자.
     * 주석을 참고하여 테스트 코드를 작성하고, 테스트를 실행시켜서 메시지가 잘 나오는지 확인한다.
     */
	
	public static void main(String[] args) throws InterruptedException {
		testExtendedThread();
		System.out.println("-----------------");
		testRunnableThread();
    }
	
    static void testExtendedThread() throws InterruptedException {
        // 하단의 ExtendedThread 클래스를 Thread 클래스로 상속하고 스레드 객체를 생성한다.
        Thread thread = new ExtendedThread("ExtendedThread");

        // 생성한 thread 객체를 시작한다.
        thread.start(); //18:49:26.482 [Thread-3] INFO concurrency.stage0.ThreadTest - hello thread

        // thread의 작업이 완료될 때까지 기다린다.
        thread.join();
    }

    private static final class ExtendedThread extends Thread { //final class는 상속 불가 + 내부 method override 불가

        private String message;

        private static final Random random = new Random();

        public ExtendedThread(final String message) {
            this.message = message;
        }

        @Override
        public void run() {
        	System.out.println(message);

            //랜덤 딜레이 그냥 넣어봄.
            String threadName = Thread.currentThread().getName();
            System.out.println("- " + threadName + " has been started");
            int delay = 1000 + random.nextInt(4000);
            try {
                Thread.sleep(delay); //1초 이상 6초 미만의 랜덤 딜레이
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("- " + threadName + " has been ended (" + delay + "ms)");
        }
    }

    /**
     * Runnable 인터페이스를 사용하는 방법도 있다.
     * 주석을 참고하여 테스트 코드를 작성하고, 테스트를 실행시켜서 메시지가 잘 나오는지 확인한다.
     */
    static void testRunnableThread() throws InterruptedException {
        // 하단의 RunnableThread 클래스를 Runnable 인터페이스의 구현체로 만들고 Thread 클래스를 활용하여 스레드 객체를 생성한다.
        Thread thread = new Thread(new RunnableThread("testRunnableThread"));

        // 생성한 thread 객체를 시작한다.
        thread.start(); //18:48:46.625 [Thread-3] INFO concurrency.stage0.ThreadTest - hello thread

        // thread의 작업이 완료될 때까지 기다린다.
        thread.join();
    }


    private static final class RunnableThread implements Runnable { //final class는 상속 불가 + 내부 method override 불가

        private String message;

        public RunnableThread(final String message) {
            this.message = message;
        }

        @Override
        public void run() {
        	System.out.println(message);
        }
    }

    /*

    ---
    extends Thread vs implement runnerable

    둘 다 같은데,

    자바에서는 다중 상속을 하용하지 않기 때문에, Thread 클래스를 확장하는 클래스는 다른 클래스를 상속받을 수 없습니다.
    반면에 Runnable 인터페이스를 구현했을 경우에는 다른 인터페이스를 구현할 수 있을 뿐만 아니라,
    다른 클래스도 상속받을 수 있습니다. 따라서 해당 클래스의 확장성이 중요한 상황이라면 Runnable 인터페이스를 구현하는 것이 더 바람직


     */
}
