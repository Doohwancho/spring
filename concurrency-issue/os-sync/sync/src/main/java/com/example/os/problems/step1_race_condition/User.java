package thread.level2_problem_race_condition;

import main.java.com.example.os.problems.step1_race_condition.BankAccount;

public class User implements Runnable{
	
	private BankAccount account;
	private String name;
	
	public User(BankAccount account, String name) {
		this.account = account;
		this.name = name;
	}

	@Override
	public void run() {
		
		if(name.equals("철수") && account.getBalance() >= 5000) {
			System.out.println(this.name+" 가 사용 시작했습니다."); 
			account.withdraw(5000);
			System.out.println(this.name+"은 통장에 5000원이 있는걸 확인하고 5000원을 꺼냈습니다.");
			System.out.println(this.name+"가 인출한 이후 남은 돈의 액수:"+account.getBalance());
			System.out.println(this.name+" 가 사용 끝났습니다."); 
		} else if(name.equals("영희")  && account.getBalance() >= 3000) {
			System.out.println(this.name+" 가 사용 시작했습니다.");
			account.withdraw(3000);
			System.out.println(this.name+"은 통장에 3000원이 있는걸 확인하고 3000원을 꺼냈습니다.");
			System.out.println(this.name+"가 인출한 이후 남은 돈의 액수:"+account.getBalance());
			System.out.println(this.name+" 가 사용 끝났습니다."); 
		}
	}

}
