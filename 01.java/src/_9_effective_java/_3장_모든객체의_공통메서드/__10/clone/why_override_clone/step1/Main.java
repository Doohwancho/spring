package _9_effective_java._3장_모든객체의_공통메서드.__10.clone.why_override_clone.step1;

public class Main {
  public static void main(String args[]) throws CloneNotSupportedException {
    Test2 t1 = new Test2();
    t1.a = 10;
    t1.b = 20;
    t1.c.x = 30;
    t1.c.y = 40;

    Test2 t2 = (Test2)t1.clone();
    t2.a = 100;

    // Change in primitive type of t2 will not be reflected in t1 field, b/c .clone() is deep copy.
    t2.c.x = 300;

    // Change in object type field of t2 will not be reflected in t1(deep copy)
    System.out.println(t1.a + " " + t1.b + " " + t1.c.x + " " + t1.c.y); //10 20 30 40
    System.out.println(t2.a + " " + t2.b + " " + t2.c.x + " " + t2.c.y); //100 20 300 0

  }
}
