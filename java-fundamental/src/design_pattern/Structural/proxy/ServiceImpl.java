package design_pattern.Structural.proxy;

public class ServiceImpl implements Service{
	@Override
    public String greeting(String str) {
        return "안녕하세요 " + str + "입니다.";
    }
}
