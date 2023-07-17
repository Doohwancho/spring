package design_pattern.Creational.abstract_factory;

public class Main {
    public static void main(String args[]){
        ComputerFactory computerFactory = new ComputerFactory();
        computerFactory.createComputer("LG");
    }
}


/*


---
diff 팩토리 메서드 패턴 추상 팩토리 메서드 패턴

팩토리 메서드 패턴은 한 팩토리당 .create()가 한개인데 반해,
추 팩토리 메서드 패턴은 여러개.

팩토리 메서드는 파라미터에 따라 .create(param)으로 객체 만드는데 비해,
추상 팩토리 메서드는 파라미터에 따라 어떤 팩토리를 쓸건지 결정됨


팩토리 메서드는 타입이 다른 다양한 객체를 생성해야 하는 경우, 문제가 생기는데, 이를 해결한게 추상 팩토리 메서드 패턴


 */