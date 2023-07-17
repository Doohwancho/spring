package design_pattern.J2EE.business_layer.business_delegate;

public class BusinessDelegate {
   private BusinessLookUp lookupService = new BusinessLookUp();
   private BusinessService businessService;
   private String serviceType;

   public void setServiceType(String serviceType){
      this.serviceType = serviceType;
   }

   public void doTask(){
	  //business layer(service)
      businessService = lookupService.getBusinessService(serviceType);
      businessService.doProcessing();
      
      //presentation layer(view)
      //view code
   }
}
