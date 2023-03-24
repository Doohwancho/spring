package main.java.com.example.lock.simpleLock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Step4ObjectClassLock {
    private static Random random = new Random();

    //TODO - b-13. Object lock
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    private static List list1 = new ArrayList();
    private static List list2 = new ArrayList();

    public static void stage1(String threadName){
        synchronized (lock1){
            try {
                System.out.println(threadName+"  stage1");
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(random.nextInt(100));
        }
    }

    public static void stage2(String threadName){
        synchronized (lock2){
            try {
                System.out.println(threadName+"  stage2");
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(random.nextInt(100));
        }
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
    Time taken: 2570
    List1: 2000; List2: 2000


    Q. 왜 step4가 step3보다 더 빨라졌지?

    step3에서 static에 synchronized(this) 걸면, class level lock이 된다고 함.
    근데 step4에서는 block level lock 건거니까, 더 빠름.



    ref: https://stackoverflow.com/questions/15568278/thread-synchronization-behaviour

    thread2는 thread1이 stage1() 끝나길 기다렸다가, 끝나서 thread1이 stage2() 실행할 때, thread2가 stage1()을 뒤쫓아가는 식.

    간략히 코드로 보면,
    class Untitled {
        public static void main(String[] args) {
            MyRunnable r1 = new MyRunnable();
            Thread t1 = new Thread(r1,"Thread 1:");
            Thread t2 = new Thread(r1,"Thread 2:");
            t1.start();
            t2.start();

        }
    }

    class MyRunnable implements Runnable
    {
        String s1 = "Hello World";
        String s2 = "Hello New World";
        public void run()
        {
            synchronized(s1)
            {
                for(int i =0;i<3;++i)
                System.out.println(Thread.currentThread().getName()+s1);

            }
            synchronized(s2)
            {
                for(int i = 0;i<3;++i)
                System.out.println(Thread.currentThread().getName()+s2);
            }

        }
    }

    OUTPUT:

    Thread 1:Hello World
    Thread 1:Hello World
    Thread 1:Hello World
    Thread 1:Hello New World
    Thread 2:Hello World
    Thread 1:Hello New World
    Thread 2:Hello World
    Thread 1:Hello New World
    Thread 2:Hello World
    Thread 2:Hello New World
    Thread 2:Hello New World
    Thread 2:Hello New World


    그렇다면, step4에서, thread2가 한단계 밀리긴 하는데, 그래도 동시에 실행하는 것 같은 퍼포먼스가 나오는건 이해함.

    근데 이해가 안가는건, step3에서, 왜 속도가 2배나 느리지? 똑같은 blocking인데?




    TODO - b-14. Object lock
    Q. 왜 lock을 list1, list2에 걸어주지 않고, 별개의 Object를 만들어서 거기에 걸었지?

    A. list1,2에 synchronized(list1) 걸어도 되긴 하는데, 별도의 lock을 정의하고 쓰는게 better-practice.
       왜냐하면, list1,2는 언제든지 변경될 수 있기 때문에, list1,2에 lock을 걸어놓으면, 다른 메서드에서 list1,2를 변경할 때, lock을 걸어놓은 메서드들이 모두 끝날 때 까지 기다려야 하기 때문에,



    //step2) 3기준, 동작 순서

    Starting...
    Thread-0  stage1
    Thread-1  stage1
    Thread-0  stage2
    Thread-1  stage2
    Thread-0  stage1
    Thread-0  stage2
    Thread-1  stage1
    Thread-0  stage1
    Thread-1  stage2
    Thread-0  stage2
    Thread-1  stage1
    Thread-1  stage2
    Time taken: 11
    List1: 6; List2: 6

    위에서 얘기한 것 처럼, 서로 밀리면서 동시에 실행되는 것 처럼 보이는 효과가 나옴.


     */
}
