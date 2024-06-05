package _1_syntax._2_syntax_details.inheritance.override.ex1_부모타입으로_선언되면_부모메서드씀;

public class Main {
    public static void main(String[] args) {
        One one = new Two(10);
        System.out.println(one.getArea()); //자식의 getArea()가 아닌, 부모의 getArea()가 나온다.
    }
}
