package chain_of_responsibility;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        ManagerPower manager = new ManagerPower();
        DirectorPower director = new DirectorPower();
        VicePresidentPower vp = new VicePresidentPower();
        PresidentPower president = new PresidentPower();
        
        manager.setSuccessor(director); //에러 나왔을 때, 다음 책임자 임명 
        director.setSuccessor(vp);
        vp.setSuccessor(president);

        try {
            while (true) {
                System.out.println("Enter the amount to check who should approve your expenditure.");
                System.out.print(">");
                double d = Double.parseDouble(new BufferedReader(new InputStreamReader(System.in)).readLine());
                manager.processRequest(new PurchaseRequest(0, d, "General"));
           }
        } catch(Exception e) {
            System.exit(1);
        }
    }
}


/*

---
outcome

Enter the amount to check who should approve your expenditure.
>1000
Manager will approve $1000.0
Enter the amount to check who should approve your expenditure.
>10000
Vice President will approve $10000.0
Enter the amount to check who should approve your expenditure.
>20000
President will approve $20000.0



---
structure

- PurchasePower
	- ManagerPower
	- DirectorPower
	- VicePresidentPower
	- PresidentPower

- PurchaseRequest


---
what is chain of responsibility?

try~catch 문과 비슷

매니저 수준에서 핸들링이 안되네? 디렉터한테 책임 던짐.
디렉터 수준에서 핸들링이 안되네? VP한테 책임 던짐.
VP 수준에서 핸들링 안되네? CEO한테 책임 던짐.


*/