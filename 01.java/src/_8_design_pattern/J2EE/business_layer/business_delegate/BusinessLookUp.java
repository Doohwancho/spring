package _8_design_pattern.J2EE.business_layer.business_delegate;

public class BusinessLookUp {
   public BusinessService getBusinessService(String serviceType){ //얘는 어떤 서비스인지 결정+DI만 하는 역할 
   
      if(serviceType.equalsIgnoreCase("EJB")){
         return new EJBService();
      }
      else {
         return new JMSService();
      }
   }
}
