package oop._1_상태_데이터의_캡슐화.nested_class.정적_멤버_클래스.utility;

public class Main {
    
    public static void main(String[] args) {
        int square = Utility.MathHelper.square(5);
        int cube = Utility.MathHelper.cube(3);
        
        System.out.println(square);
        System.out.println(cube);
    }
    
}
