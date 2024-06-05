package _1_syntax._2_syntax_details.inheritance.override.ex1_부모타입으로_선언되면_부모메서드씀;

public class Two extends One{
   int x;
   Two(int x) {
       super(x, x);
   }
   int getArea(int x){
       return x;
   }
}
