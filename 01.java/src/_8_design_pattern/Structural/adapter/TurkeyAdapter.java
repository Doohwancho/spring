package _8_design_pattern.Structural.adapter;

public class TurkeyAdapter implements Duck {
	Turkey turkey;
 
	public TurkeyAdapter(Turkey turkey) {
		this.turkey = turkey;
	}
    
	public void quack() {
		turkey.gobble();
	}
  
	public void fly() { //Duck 인터페이스에 맞게 기존 Turkey의 인터페이스를 바꾼다. 
		for(int i=0; i < 5; i++) {
			turkey.fly();
		}
	}
}
