package oop._1_상태_데이터의_캡슐화.nested_class.익명_클래스.calculator;

public class Calculator {
    private int x;
    private int y;
    
    public Calculator(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int plus() {
        Operator operator = new Operator() {
            private static final String COMMENT = "더하기"; // 상수
            // private static int num = 10; // 상수 외의 정적 멤버는 불가능
            
            @Override
            public int plus() {
                // Calculator.plus()가 static이면 x, y 참조 불가
                return x + y;
            }
        };
        return operator.plus();
    }
}

