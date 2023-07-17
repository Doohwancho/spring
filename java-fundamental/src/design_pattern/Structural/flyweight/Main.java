package design_pattern.Structural.flyweight;

public class Main {
    public static void main(String[] args) {
        CarFactory factory = new CarFactory();

        System.out.println(factory.getCar("Car A"));
        System.out.println(factory.getCar("Car B"));
        System.out.println(factory.getCar("Car C"));
        System.out.println(factory.getCar("Car B"));
        System.out.println(factory.getCar("Car A")); //기존에 생성된 객체를 그대로 반환해준
        System.out.println(factory.getCar("Car A"));
    }
}

/*

flyweight = weighs no more than 115 pounds

너무 무거운 객체 관리하는 디자인 패턴.

by 객체 중복 저장 방지.

왜? 객체가 쓰는 자원이 아주 많을 경우, 공통 자 형태로 관리하면서 요청있을 때 만 잠깐 제공해주는 식.

아니면 자주 생성하지도 않고, 자주 쓰지도 않은 자원 항상 새롭게 만들지 말고, 예전에 만든거 가져다 쓰는 식. 

*/