package main.java.com.example.lock.simpleLock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Step1Default {
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

        process(); //case1) process()를 한번만 호출
//        process(); //case2) process()를 두번 호출

        long end = System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start));
        System.out.println("List1: " + list1.size() + "; List2: " + list2.size());
    }

    /*
    console.log

    //case1) process()를 한번만 호출
    Starting...
    Time taken: 2531
    List1: 1000; List2: 1000


    Q. 왜 시간이 2531이 나오는가?

    1ms로 총 2000번 insert 하니까 2000이고, 나머지 531ms은 overhead이다.


    //case2) process()를 두번 호출

    Starting...
    Time taken: 5093
    List1: 2000; List2: 2000


    소요 시간이 한번 호출한 거에 2배가 됬네.

     */
}
