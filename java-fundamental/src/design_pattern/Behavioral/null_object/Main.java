package design_pattern.Behavioral.null_object;

public class Main {
   public static void main(String[] args) {

      AbstractCustomer customer1 = CustomerFactory.getCustomer("Rob");
      AbstractCustomer customer2 = CustomerFactory.getCustomer("Bob");
      AbstractCustomer customer3 = CustomerFactory.getCustomer("Julie");
      AbstractCustomer customer4 = CustomerFactory.getCustomer("Laura");

      System.out.println(customer1.getName());
      System.out.println(customer2.getName());
      System.out.println(customer3.getName());
      System.out.println(customer4.getName());
   }
}

/*

---
structure

- AbstractCustomer
	- RealCustomer
	- NullCustomer
	
- CustomerFactory



---
what is null object pattern?

db에 query 날렸는데, search해서 없으면 null 반환하는 것


---
why important?

return null하는게 아니라 null Object를 반환하는 것.

장점
1. null 검사 필요 줄어듬
2. 의도치 않은 NullPointerException나 null 확인 코드 피할 수 있음. 

단점 
1. null 객체는 null 맞고 깨지지 않아 왠만하면 작동하나, 문제 감지나 찾기 힘들어질 수 있다.  
2. null 검사 if else 줄이자고 객체를 만드는건, 배보다 배꼽이 더 크다. 

*/