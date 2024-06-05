package main.java.com.example.lock.simpleLock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Step3Synchronized {
    private static Random random = new Random();

    private static List list1 = new ArrayList();
    private static List list2 = new ArrayList();

    public static synchronized void stage1(String threadName){
        try {
            System.out.println(threadName+"  stage1");
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list1.add(random.nextInt(100));
    }

    public static synchronized void stage2(String threadName){
        try {
            System.out.println(threadName+"  stage2");
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list2.add(random.nextInt(100));
    }

    public static void process(String threadName){
        for(int i=0; i<3; i++){
            stage1(threadName);
            stage2(threadName);
        }
    }

    public static void main(String[] args){
        System.out.println("Starting...");
        long start = System.currentTimeMillis();

        Thread t1 = new Thread(new Runnable(){
            public void run(){
                process(Thread.currentThread().getName());
            }
        });

        Thread t2 = new Thread(new Runnable(){
            public void run(){
                process(Thread.currentThread().getName());
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start));
        System.out.println("List1: " + list1.size() + "; List2: " + list2.size());
    }

    /*
    //step1) 1000기준 성능 측정
    console.log

    Starting...
    Time taken: 5100
    List1: 2000; List2: 2000


    List의 갯수도 2000개 씩으로 딱 맞아 떨어지고.
    Step1에서 process 2번 호출한 것과 비슷한 소요시간이 드네.
    내부적으로 이런식으로 동작하는 듯.


    TODO - b.13. class level lock vs block level lock
    Q. 왜 느림?
    static이 붙은 상태에서 synchronized(this) 하면 block level lock이 아닌 class level lock으로 동작한다고 함.
    그래서 느린 듯?



    //step2) 3기준, 동작 순서
    Starting...
    Thread-0  stage1
    Thread-0  stage2
    Thread-0  stage1
    Thread-0  stage2
    Thread-0  stage1
    Thread-0  stage2
    Thread-1  stage1
    Thread-1  stage2
    Thread-1  stage1
    Thread-1  stage2
    Thread-1  stage1
    Thread-1  stage2
    Time taken: 17
    List1: 6; List2: 6


    thread-0이 stage1(), stage2() 둘다 도는걸 끝내야,
    thread-1이 stage1(), stage2() 둘다 도는걸 끝낼 수 있음.

    그래서 시간이 2배 걸리는구나.

    thread-0이 stage1()을 먼저 점유해서 얻은 lock을, stage1()이 끝나면 바로 반환해야 하는데,
    내부적으로 이해할 수 없는 무언가가, thread-0의 동작이 모두 끝내야만 lock을 반환하도록 되있는 듯?


    Starting...
    Thread-1  stage1
    Thread-0  stage1
    Thread-0  stage2
    Thread-0  stage1
    Thread-0  stage2
    Thread-0  stage1
    Thread-0  stage2
    Thread-1  stage2
    Thread-1  stage1
    Thread-1  stage2
    Thread-1  stage1
    Thread-1  stage2
    Time taken: 17
    List1: 6; List2: 6


    다시 돌려 봐도, thread-1이 stage1()을 먼저 점유했더라도,
    thread-0이 stage1(), stage2() 이걸 다 끝내야,
    thread-1이 stage1(), stage2()을 마저 끝내네?

    근데 thread1이 stage1() 점령 한 다음, 얘는 일 다 끝내고 lock 반환하는게 아니라, stage1() 끝나면 바로 반환하네?

    Starting...
    Thread-0  stage1
    Thread-0  stage2
    Thread-0  stage1
    Thread-0  stage2
    Thread-1  stage1
    Thread-1  stage2
    Thread-1  stage1
    Thread-1  stage2
    Thread-1  stage1
    Thread-1  stage2
    Thread-0  stage1
    Thread-0  stage2
    Time taken: 17
    List1: 6; List2: 6

    다 일 끝내고 반환하는게 아니라, 지맘대로 중간에 반환하네??


     */
}
