package design_pattern.Structural.proxy;

public class Main {
	public static void main(String[] args) {
        Service service = new Proxy(); //애초에 ServiceImpl 객체 생성조차 안하고 Proxy로 생성하는구나. Proxy는 ServiceImpl의 exact copy + 스니핑 코드
        System.out.println(service.greeting("hello world!"));
    }
}
