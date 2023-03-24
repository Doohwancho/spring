package main.java.com.example.lock.simpleLock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Step2NoLock {
    private static Random random = new Random();

    private static List list1 = new ArrayList();
    private static List list2 = new ArrayList();

    public static void stage1(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list1.add(random.nextInt(100));
    }

    public static void stage2(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        list2.add(random.nextInt(100));
    }

    public static void process(){
        for(int i=0; i<1000; i++){
            stage1();
            stage2();
        }
    }

    public static void main(String[] args){
        System.out.println("Starting...");
        long start = System.currentTimeMillis();

        Thread t1 = new Thread(new Runnable(){
            public void run(){
                process();
            }
        });

        Thread t2 = new Thread(new Runnable(){
            public void run(){
                process();
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
    console.log

    Starting...
    Time taken: 2548
    List1: 1958; List2: 1963


    Q. 왜 List의 갯수가 2000이 안맞지?

    transaction, lock을 안잡아 줬으니까, list1에 thread1, thread2가 동시접근해서 read-modify-write 할 때 문제가 생김.

     */
}
