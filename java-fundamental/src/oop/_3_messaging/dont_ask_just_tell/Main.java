package oop._3_messaging.dont_ask_just_tell;

public class Main {

  public static void main(String[] args) {
    //Don't ask, Just Tell!
    //핵심: 목적/의도/why 를 메서드 이름으로 유추할 수 있어야 한다.

    //장점
    //1. 메서드 명만 보고 의도 파악 가능
    //2. OOP의 꽃이 내부 private 변수 은닉화인데, getter가 은닉에 구멍 뚫는거라, 이건 안티 패턴.

    //case1)
    Distance distance = new Distance(100);

    //Don't
    int km1 = distance.getMeter() * 1000;

    //Do
    int km2 = distance.toKiloMeter(); //'why'인 의도가 들어가 있다.


    //case2)
    Bank bank = new Bank("normal", 10000);

    //Don't
    bank.setBalance(bank.getBalance() + 90000);

    //Do
    bank.입금(90000); //'why'인 의도가 들어가 있다.
    bank.출금(200000);


    //case3)
    //Don't
    bank.setState("VIP"); //어떤 의도인지 명확하게 드러나지 않는다.

    //Do
    bank.upgradeToVIP(); //어떤 의도인지 'why'가 명확하게 드러난다.

  }

}
