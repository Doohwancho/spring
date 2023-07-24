package oop._1_상태_데이터의_캡슐화.nested_class.정적_멤버_클래스.utility;

public class Utility {
    
    private Utility() {}
    public static class MathHelper {
        
        public static int square(int number) {
            return number * number;
        }
        
        public static int cube(int number) {
            return number * number * number;
        }
    }
}
