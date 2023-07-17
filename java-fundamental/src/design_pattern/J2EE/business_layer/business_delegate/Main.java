package design_pattern.J2EE.business_layer.business_delegate;

public class Main {
   public static void main(String[] args) {

      BusinessDelegate businessDelegate = new BusinessDelegate();
      businessDelegate.setServiceType("EJB");

      Client client = new Client(businessDelegate);
      client.doTask();

      businessDelegate.setServiceType("JMS");
      client.doTask();
   }
}

/*

---
output

Processing task by invoking EJB Service
Processing task by invoking JMS Service


---
structure

- BusinessDelegate
	-> BusinessService(interface)
		- EJBService
		- JMSService
	-> BusinessLookUp
- Client


---
why use business delegate pattern?

business tier(service layer)와 presentation tier(view layer) 분리 목적



*/