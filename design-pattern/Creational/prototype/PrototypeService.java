package prototype;

import java.util.HashMap;

public class PrototypeService { //Product로 부터 독립된 객체 
	private HashMap showcase = new HashMap<>();
	
	public void register(String name, Product proto) { //name을 키 삼아 객체 등
		showcase.put(name, proto);
	}

	public Product create(String protoName) {
		return ((Product)showcase.get(protoName)).createClone(); 
	}
}
