package jdk_versions.jdk8.future.what.step1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//Q. what is Future?

//A. 비동기로 실행되는 코드를 blocking 할 수 있게 하거나, 완료되면 .get()할 수 있게 해줌.
public class Main {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    //step1)
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    Future<Integer> future = executorService.submit(() -> {
      System.out.println("Performing a time-consuming task...");
      Thread.sleep(2000);
      return 123;
    });

    System.out.println("Doing something else while the task is being performed...");

    Integer result = future.get();  // This will block until the task is done

    System.out.println("The result of the task is: " + result);

    executorService.shutdown();

    /*
    console.log

    Doing something else while the task is being performed...
    Performing a time-consuming task...
    The result of the task is: 123
     */

    System.out.println("================================");
  }

}
