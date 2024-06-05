package _2_oop._1_상태_데이터의_캡슐화.solid._3_liskov_substitution.example2_square.step1;

public class Main {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(10);
        rectangle.setHeight(5);

        System.out.println(rectangle.getArea()); //50


        Rectangle square = new Square();
        square.setWidth(10);
        square.setHeight(5);

        System.out.println(square.getArea()); //25 -> error! 애초에 정사각형이니까 가로세로 길이가 다를 때 부터 throw Exception 했었어야지!
    }
}

/*

---
what is this code?


Square(정사각형) extends Rectangle(직사각형)

언듯 생각하기에, 직사각형 안에 정사각형이 포함되는 듯 하나,
실제 코드 작성해보면, 정사각형은 가로세로길이가 같아야된다는 조건이 하나 더 붙어야 하기 때문에, 결과값이 이상하게 나온다.




 */