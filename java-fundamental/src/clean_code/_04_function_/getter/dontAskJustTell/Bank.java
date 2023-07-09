package clean_code._04_function_.getter.dontAskJustTell;

public class Bank {
  private int balance;
  private String state;

  Bank(String state, int balance){
    this.balance = balance;
    this.state = state;
  }

  //Don't
  public int getBalance(){
    return this.balance;
  }

  //Don't
  public void setBalance(int balance){
    this.balance = balance;
  }

  //Do
  public void 입금(int balance) { this.balance += balance; }

  public void 출금(int balance) {
    if(this.balance - balance < 0){
      throw new RuntimeException("0원 이하로 잔액이 내려갈 수 없습니다.");
    }
    this.balance -= balance;
  }

  //Don't
  public void setState(String state){
    this.state = state;
  }

  //Do
  public void upgradeToVIP() { this.state = "VIP"; }

}
