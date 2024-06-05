package _8_design_pattern.Structural.proxy;

public class Proxy implements Service{
	Service service;
    @Override
    public String greeting(String str) {
        System.out.println("호출에 대한 흐름 제어가 주목적이며, 반환 결과를 그대로 전달한다."); //여기에 스니핑(?!) 코드 심는 
        service = new ServiceImpl();
        return service.greeting(str);
    }
}
