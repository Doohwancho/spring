package main.java.com.example.thread.step5_interrupt;

public class App {
    public static void main(String[] args) throws InterruptedException{
        System.out.println("Starting...");

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    System.out.println("Running...");

                    boolean status = Thread.currentThread().isInterrupted(); //인터럽트 되었을 때,

                    if (status) { //수동으로 처리해주어야 한다.
                        System.out.println("Interrupted!");
                        break;
                    }
                }
            }
        });

        t1.start();

        Thread.sleep(1000);

        t1.interrupt(); //주의! 그냥 t1.interrupt();한다고 멈추는게 아님. isInterrupted()를 통해 확인해야함.

        t1.join();

        System.out.println("Finished!");
    }
}
