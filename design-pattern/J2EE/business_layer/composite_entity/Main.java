package business_layer.composite_entity;

public class Main {
   public static void main(String[] args) {
       Client client = new Client();
       client.setData("Test", "Data");
       client.printData();
       client.setData("Second Test", "Data1");
       client.printData();
   }
}

/*

---
structure


- Client
	-> CompositeEntity
		-> CoarseGrainedObject
			-> DependentObject1
			-> DependentObject2

단일 Entity Bean으로 CoarseGrainedObject가 의존 객체들을 그룹지어 실행 성능을 높이는 기능


*/
