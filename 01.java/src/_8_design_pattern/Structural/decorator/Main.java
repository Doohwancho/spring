package _8_design_pattern.Structural.decorator;

public class Main {
	public static void main(String[] args) {
		Beverage drink = new Whip(new Mocha(new Decaf())); //highlight!
		System.out.println(drink.getDescription());
		System.out.println(drink.cost());
		
		Beverage drink2 = new Whip(new Espresso());
		System.out.println(drink2.getDescription());
		System.out.println(drink2.cost());
	}
}

/*

diff adapter vs decorator

adaptor은 기존 인터페이스에 새로운 인터페이스 추가해야 해서 충돌날 때, 하나의 인터페이스를 다른 인터페이스 규격으로 바꾸면서 호환해주는 거라면,

decorator는인터페이스 통합엔 관심 없고, 기존 시스템에 새로운 기능 덧 입히고 싶을 때 사

*/