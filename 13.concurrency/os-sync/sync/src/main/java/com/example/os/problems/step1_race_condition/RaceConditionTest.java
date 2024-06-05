package main.java.com.example.os.problems.step1_race_condition;

public class RaceConditionTest {
	public static void main(String[] args) throws InterruptedException {
		BankAccount account = new BankAccount(5000);
		
		thread.level2_problem_race_condition.User user1 = new thread.level2_problem_race_condition.User(account, "철수");
		thread.level2_problem_race_condition.User user2 = new thread.level2_problem_race_condition.User(account, "영희");
		
		Thread t1 = new Thread(user1);
		Thread t2 = new Thread(user2);
		
		System.out.println("시작시 돈의 액수: "+account.getBalance());
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println("끝났을 당시 돈의 액수: "+ account.getBalance());
	}

}

/*

---
what is race condition?


둘 이상 쓰레드가 같은 자원을 가지고 경쟁한다.



---
what is this code?


race condition의 문제점을 나타낸 코드.



통장에 5천원이 있다.

철수는 통장을 보고 5천원 이상 돈이 남아있으면, 5천원을 인출한다.
영희도 통장을 보고 3천원 이상 돈이 남아있으면, 3천원을 인출한다.

정상적인 상황이라면, 철수나 영희 둘 중 한명이 인출하면, 통장의 남은 돈이 0원이거나 2천원이니까, 나머지 한명은 인출을 못해야 한다.

함 볼까?


---
console창


시작시 돈의 액수: 5000
영희 가 사용 시작했습니다.
철수 가 사용 시작했습니다. --> context switching
철수은 통장에 5000원이 있는걸 확인하고 5000원을 꺼냈습니다. --> context switching
영희은 통장에 3000원이 있는걸 확인하고 3000원을 꺼냈습니다. ---> ????? 철수가 5천원 꺼내갔으니까 0원 있어야 정상인데, 3천원 있는걸 확인했다고?
철수가 인출한 이후 남은 돈의 액수:-3000 --> ???? 마이너스 3천원을 가졌다는게 말이 됨? 
영희가 인출한 이후 남은 돈의 액수:-3000 --> ????
철수 가 사용 끝났습니다.
영희 가 사용 끝났습니다.
끝났을 당시 돈의 액수: -3000 --> ????


왜 통장에 마이너스 3천이 찍혔나?
마이너스 통장이라서?


---
problem of race condition1: 무결성 보장X 

무결성: db의 값과 현실세계의 값이 같아야 한다.


무결성이 깨졌다.
어떻게 이 문제를 해결하지?

방법:
철수가 통장 잔고 보고 5천원 인출하는게 끝날 때 까지,
영희는 기다려야 한다. 

화장실에 들어가서 일 다 보고 나올 떄 까지,
다음 사람은 기다려야 한다.

화장실 들어갔다 나오는 요 구간,
남에게 침범받아서는 안되는 구간을 임계 구역(critical section)이라고 한다.
critical section이 있어야 데이터의 무결성이 보장된다.


화장실에 한번에 한놈만 들어가!
critical section을 보장해주는 것.
이게 동기화(synchronized) 이다. 




---
problem of race condition2: 정합성 보장X

데이터 정합성: 데이터가 모순없이 일치해야 함.


만약에 철수랑 영희가 같은 account에 동시에 각각 5000원, 3000원 입금하려고 한다.

1. 철수가 RAM에 공유자원에서 account.balance를 local CPU cache에 복사해서 read
2. 철수가 account.balance += 5000;
3. 영희로 context switching이 일어남
4. 영희도 RAM에 공유자원에서 account.balance를 local CPU cache에 복사해서 read
5. 영희도 account.balance += 3000;
6. 영의는 RAM 공유자원에 3000인 account.balance을 write(); 함
7. 철수로 context switching이 일어남
8. 철수도 RAM 공유자원에 5000인 account.balance을 write(); 함
9. 철수, 영희가 같은 account에 각각 5000,3000원씩 넣었으니까 총 8000원이 있어야 하는데, 5000원 밖에 없음. 엥?



이 문제를 어떻게 해결하지?

해결법1: RAM에 있던 공유자원을 local CPU cache로 일어나서 벌어진 문제니까, 두 쓰레드를 RAM에 공유자원을 쓰게 만들자! -> volatile

*/