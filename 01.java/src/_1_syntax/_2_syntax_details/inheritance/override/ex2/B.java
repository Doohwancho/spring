package _1_syntax._2_syntax_details.inheritance.override.ex2;

public class B extends A{
   public void paint() {
       super.draw();
       System.out.println("C");
       this.draw();
   }
   
   public void draw() {
       System.out.println("D");
   }
}
