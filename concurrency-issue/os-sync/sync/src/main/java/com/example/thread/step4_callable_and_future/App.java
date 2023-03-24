package main.java.com.example.thread.step4_callable_and_future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        //TODO - b-17. Callable and Future
        Future<Integer> future = executor.submit(new Callable<Integer>() { //Runnable이 아니라 Callable을 쓰면,
            @Override
            public Integer call() throws Exception { //run()이 아니라 call()을 오버라이드 하는데, 얘는 return 값이 있고, exception을 throw 할 수 있다.
                Random random = new Random();
                int duration = random.nextInt(4000);

                if(duration > 2000) {
                    throw new Exception("Sleeping for too long."); //Exception도 던질 수 있다.
                }

                System.out.println("Starting ...");

                try {
                    Thread.sleep(duration);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Finished.");

                return duration; //run()과는 다르게 return 값이 있음
            }
        });

        executor.shutdown();

        try{
            System.out.println("Result is: " + future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
