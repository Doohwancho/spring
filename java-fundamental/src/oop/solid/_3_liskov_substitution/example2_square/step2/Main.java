package oop.solid._3_liskov_substitution.example2_square.step2;

public class Main {
    public static void main(String[] args) {
        Shape rectangle = new Rectangle(10, 5);
        Shape square = new Square(5);
        System.out.println(rectangle.getArea()); //50
        System.out.println(square.getArea()); //25
    }
}

/*

Square extends Rectangle이 아니라,

Square extends Shape
Rectangle extends Shape
한 후, 정사각형, 직사각형 가로세로 결정하는 메서드 내부를 다르게 구현함.



Q. 이게 리스코프 치환원칙이랑 뭔상관인데?

리스코프 치환 원칙이라는게, 부모대신 자식이 가도 아무 문제가 없어야된다잖아?

step1엔 부모, 자식 간 상속관계를 잘못 파악했기 때문에,
자식인 Square가 부모인 Rectangle로 썼을 때, 가로세로 길이 안맞아서 문제가 발생했었음.

step2엔 이 둘이 부모-자식 관계가 아님을 파악하고, 적절한 부모인 Shape을 썼더니,
자식이 자기 타입 쓰던, 부모 타입써서 호환하던 문제발생 안함.


새로운 코드 추가할 때, 기존 객체에 있는 메서드랑 겹치는게 많네? -> 무지성 상속, 오버라이딩
이러면 나중에 step1 처럼 꼬이고 골치아파지니까,
리스코프 치환 원칙은 상속 남발하지 말고, 올바른 부모-자식 관계일 떄에만 신중히 상속 쓰라는 말 같다.


 */
