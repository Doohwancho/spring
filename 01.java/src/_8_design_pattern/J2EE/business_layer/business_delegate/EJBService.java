package _8_design_pattern.J2EE.business_layer.business_delegate;

public class EJBService implements BusinessService {

   @Override
   public void doProcessing() {
      System.out.println("Processing task by invoking EJB Service");
   }
}
