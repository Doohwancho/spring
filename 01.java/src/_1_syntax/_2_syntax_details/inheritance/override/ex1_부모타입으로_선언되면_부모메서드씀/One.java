package _1_syntax._2_syntax_details.inheritance.override.ex1_부모타입으로_선언되면_부모메서드씀;

public class One {
    int x,y;
    
    One(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    int getArea(){
        return x + y;
    }

}
