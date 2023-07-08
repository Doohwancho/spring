package jdk_versions.jdk8.future.what.step2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Main2 {

  public static void main(String[] args) throws ExecutionException, InterruptedException{
    //step2) 걍 다른 예제임

    ExecutorService es = Executors.newCachedThreadPool();
    FutureTask<String> f = new FutureTask<>(()->{
      Thread.sleep(2000);
      System.out.println("Async!");
      return "Hello";
    });
    es.execute(f);

    System.out.println(f.isDone()); //즉시 리턴(작업이 완료되었는지)
    Thread.sleep(2100);
    System.out.println("Exit!");
    System.out.println(f.isDone());
    System.out.println(f.get());

    /*
      console.log

      false   -> 잠깐 기다렸다가..
      Async!  -> 2초 뒤에 이게 print 되고
      Exit!   -> 이게 출력 되고,
      true    -> f의 상태를 보면 false -> true로 바뀌어져 있다.
      Hello   -> f.get()하면 값이 받아와져있다.
     */

  }

}
