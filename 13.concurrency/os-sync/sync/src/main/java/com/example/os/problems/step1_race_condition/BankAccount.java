package main.java.com.example.os.problems.step1_race_condition;

public class BankAccount {

	private int balance;
	
	public BankAccount(int asset){
		balance = asset;
	}
	
	public void deposit(int amount) {
		balance += amount;
	}
	public void withdraw(int amount) {
		balance -= amount;
	}
	
	public int getBalance() {
		return balance;
	}
}
