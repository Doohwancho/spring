package _8_design_pattern.Structural.bridge;

public class Main {
	public static void main(String[] args) {
		Shape tri = new Triangle(new RedColor());
		tri.applyColor();
		
		Shape pent = new Pentagon(new GreenColor());
		pent.applyColor();
	}
}

/*

Shape 따로, Color 따로 둘로 나눠서 관리하는데,
최상위 추상 클래스 Shape에서 인터페이스인 Color랑 이어

실전에서는 GUI부분을 최상위 추상 클래스로,
다양한 APIs를 implementation으로 받아 주입함

*/