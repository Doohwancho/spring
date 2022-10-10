package null_object;

public class CustomerFactory {
	
   public static final String[] names = {"Rob", "Joe", "Julie"};

   public static AbstractCustomer getCustomer(String name){
   
      for (int i = 0; i < names.length; i++) {
         if (names[i].equalsIgnoreCase(name)){ //set, map에 있는 놈만 객체 반
            return new RealCustomer(name);
         }
      }
      return new NullCustomer(); //데이터베이스에 없으면 null 반환 
   }
}
