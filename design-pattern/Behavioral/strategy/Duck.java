package strategy;

public abstract class Duck {
	
	private final String name;
	
	//인터페이스의 변수를 만들어만 놓고, Duck을 상속받는 RedHeadDuck에서 입맛에 맞게 특정 패턴/행동을 넣음
	FlyBehavior flyBehavior;
	QuackBehavior quackBehavior;
	
	public Duck(String name){
		this.name = name;
	}

	public void swim(){
		System.out.println(this.name+ " duck swim!");
	}
	
	public abstract void display(); 
	
	//인터페이스 안에 객체의 종류는 전혀 신경쓸 필요 없음.fly()를 실행한 다는 것 만 중요**
	public void performFly() {
		flyBehavior.fly();
	}
	
	public void performQuack() {
		quackBehavior.quack();
	}
	
	public void setPerformFly(FlyBehavior fb) {
		flyBehavior = fb;
	}

}