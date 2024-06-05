package _1_syntax._2_syntax_details.memory.reference.weak_reference;


//A weak reference is a reference that isn't strong enough to force an object to remain in _3_syntax_details.memory.
// It allows the garbage collector to determine reachability, so you don't have to do it yourself.
// If an object is only weakly reachable, it will be discarded at the next garbage collection cycle.
public class WeakReference {
  public static class Referred {
    protected void finalize() {
      System.out.println("Good bye cruel world");
    }
  }

  public static void collect() throws InterruptedException {
    System.out.println("Suggesting collection");
    System.gc();
    System.out.println("Sleeping");
    Thread.sleep(5000);
  }

  public static void main(String args[]) throws InterruptedException {
    System.out.println("Creating weak references");

    // This is now a weak reference.
    // The object will be collected only if no strong references.
    Referred strong = new Referred();
    java.lang.ref.WeakReference<Referred> weak = new java.lang.ref.WeakReference<Referred>(strong);

    // Attempt to claim a suggested reference.
    WeakReference.collect();

    System.out.println("Removing reference");
    // The object may be collected.
    strong = null;
    WeakReference.collect();

    System.out.println("Done");
  }
}
