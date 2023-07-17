package design_pattern.J2EE.business_layer.service_locator;

public class Main {
   public static void main(String[] args) {
      Service service = ServiceLocator.getService("Service1");
      service.execute();
      service = ServiceLocator.getService("Service2");
      service.execute();
      service = ServiceLocator.getService("Service1"); //return cached service
      service.execute();
      service = ServiceLocator.getService("Service2"); //return cached service
      service.execute();		
   }
}

/*

---
structure


- Service(interface)
	- Service1
	- Service2
	
- ServiceLocator
	-> Cache
	-> InitialContext

---
포인트 

캐시 쓰네?

서비스가 자원 많이 먹으니까, 
자주 요청되고 반복되는 객체는 캐시로 바로 보내주는 것 같다. 


*/