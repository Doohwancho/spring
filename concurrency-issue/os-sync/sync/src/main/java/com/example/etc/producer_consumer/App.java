package main.java.com.example.etc.producer_consumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {
    //TODO - a.8: producer/consumer queue
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10); //내부적으로 concurrency 처리 다 해주기 때문에 synchronized 필요 없음


    private static void producer() {
        Random random = new Random();
        while (true) {
            try {
                queue.put(random.nextInt(100)); //queue의 max size 넘어가면 넣지 않고 기다리도록 내부적으로 처리되어있다.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void consumer() {
        Random random = new Random();
        while (true) {
            try {
                Thread.sleep(100);
                if (random.nextInt(10) == 0) {
                    Integer value = queue.take(); //queue의 size가 0이면 기다리도록 내부적으로 처리되어있다.
                    System.out.println("Taken value: " + value + "; Queue size is: " + queue.size());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                producer();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                consumer();
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
    }
}
