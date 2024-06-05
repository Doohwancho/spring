package _2_oop._1_상태_데이터의_캡슐화.state.immutable_object.example.복소수;

public class Main {
    
    public static final Complex ZERO = new Complex(0, 0); //thread-safe, 안전하게 공유 가능
    public static final Complex ONE = new Complex(1, 0);
    public static final Complex I = new Complex(0, 1);
    
}
