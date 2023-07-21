package defensive_programming.exception.structure._2_checked_exception_extends_Exception.how.InterruptedException;

public class Main {
    public static void main(String[] args) {
        try {
            // Simulate some processing that can be interrupted
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // Thread was interrupted, handle the interruption
            System.out.println("Thread was interrupted, failed to complete operation");
            // Restore interrupted state...
            Thread.currentThread().interrupt();
        }
    }
}
