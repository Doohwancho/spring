package oop._1_상태_데이터의_캡슐화.state.synchronization.beta_lock.step2_RelentlessLock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
  private final ReentrantLock lock = new ReentrantLock();
  private int sharedCounter = 0;

  public void incrementCounter() {
    lock.lock(); // Acquire the lock
    try {
      sharedCounter++;
      System.out.println("Counter incremented by " + Thread.currentThread().getName() + ": " + sharedCounter);
    } finally {
      lock.unlock(); // Release the lock
    }
  }

  public static void main(String[] args) {
    ReentrantLockExample example = new ReentrantLockExample();
    Runnable task = example::incrementCounter;

    Thread t1 = new Thread(task, "Thread 1");
    Thread t2 = new Thread(task, "Thread 2");
    Thread t3 = new Thread(task, "Thread 3");

    t1.start();
    t2.start();
    t3.start();
  }
}
