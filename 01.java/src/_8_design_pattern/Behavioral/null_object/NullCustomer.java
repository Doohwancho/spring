package _8_design_pattern.Behavioral.null_object;

public class NullCustomer extends AbstractCustomer {

   @Override
   public String getName() {
      return "Not Available in Customer Database";
   }

   @Override
   public boolean isNil() { //isNull()이 있으니 isNil()로 쓴건가 
      return true;
   }
}