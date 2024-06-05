package _1_syntax._2_syntax_details.inheritance.override.ex2;

public class A {
    public void paint() {
        System.out.println("A");
        draw();
    }
    
    public void draw() {
        System.out.println("B");
        draw();
    }
}
