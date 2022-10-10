package strategy;

public class Main {
	public static void main(String[] args) {
		Duck redheadDuck = new RedHeadDuck("Redhead Duck"); //다형성 - Duck으로 받음
		
		redheadDuck.performFly();
		redheadDuck.setPerformFly(new FlyWithWings()); //setter를 이용해 동적으로 기능 바꿈
		redheadDuck.performFly();
		redheadDuck.performQuack();
	}
}

/*

diff strategy pattern vs template_method pattern

둘 다 비슷하긴 한데,
템플릿 메서드 패턴은 준비된 템플릿에 중요한 알맹이 한두개 슥 바꿔서 찍어내는 거라면
전략 패턴은 모든 feature을 class화 해서 원하는 알맹이만 쏙 넣어서 먹는 


*/
