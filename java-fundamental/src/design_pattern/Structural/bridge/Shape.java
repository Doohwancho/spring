package design_pattern.Structural.bridge;

public abstract class Shape { //Color를 받는 최상위 객체는 추상 클래스
	//Composition - implementor
	protected Color color;
	
	public Shape(Color c){
		this.color=c;
	}
	
	abstract public void applyColor();
}
