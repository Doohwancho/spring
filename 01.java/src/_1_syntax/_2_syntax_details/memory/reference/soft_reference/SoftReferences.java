package _1_syntax._2_syntax_details.memory.reference.soft_reference;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

//A soft reference is exactly like a weak reference,
// except that it is less eager to throw away the object to which it refers.
// An object which is softly reachable will generally stick around for a while,
// as long as _3_syntax_details.memory is in plentiful supply.
// This makes them an excellent foundation for a cache.

public class SoftReferences {
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
    System.out.println("Creating soft references");

    // This is now a soft reference.
    // The object will be collected only if no strong references exist and the JVM really needs the _3_syntax_details.memory.
    Referred strong = new Referred();
    SoftReference<Referred> soft = new SoftReference<>(strong);

    // Attempt to claim a suggested reference.
    SoftReferences.collect();

    System.out.println("Removing reference");
    // The object may but highly likely wont be collected.
    strong = null;
    SoftReferences.collect();

    System.out.println("Consuming heap");
    try
    {
      // Create lots of objects on the heap
      List<SoftReferences> heap = new ArrayList<>(100000);
      while(true) {
        heap.add(new SoftReferences());
      }
    }
    catch (OutOfMemoryError e) {
      // The soft object should have been collected before this
      System.out.println("Out of _3_syntax_details.memory error raised");
    }

    System.out.println("Done");
  }
}
