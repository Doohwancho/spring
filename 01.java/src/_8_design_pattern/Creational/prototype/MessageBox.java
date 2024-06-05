package _8_design_pattern.Creational.prototype;

public class MessageBox implements Product { //Product를 받
	private String deco;

	public MessageBox(String deco) {
		this.deco = deco;
	}

	@Override
	public String use(String s) { //객체 앞, 뒤에 데코레이팅 해주는 함수(핵심은 아님)
		return deco + s + deco;
	}

	@Override
	public Product createClone() {
		Product p = null;
		try {
			p = (Product)clone(); //deep copy? NO!
			//p.maps = p.maps.clone();  //maps의 clone을 개발자가 따로 구현해 주어야 함. 
			
		} catch (CloneNotSupportedException ex) {
			ex.printStackTrace();
		}
		return p;
	}
}
