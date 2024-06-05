package _1_syntax._2_syntax_details.inheritance.override.ex2;

public class Main {
    public static void main(String[] args) {
        A b = new B();
        b.paint();
        b.draw();
        
        //console.log
        //BBCDD -> 아님!
        //override 부모 메서드에서 자기 함수 호출하면 자식 메서드가 호출됨
        //정답: BDCDD
    }
}
