package _1_syntax._2_syntax_details.static_.ex1;

public class Main {
   public static void main(String[] args) {
       int a;
       a = 10;
       AB.b = a;
       AB ia = new AB();
       System.out.println(AB.b++); //10
       System.out.println(ia.b); //11
       System.out.println(a); //10
       System.out.println(ia.a); //20
   }
}
