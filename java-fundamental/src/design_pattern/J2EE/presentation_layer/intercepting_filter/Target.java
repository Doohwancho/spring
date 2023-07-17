package design_pattern.J2EE.presentation_layer.intercepting_filter;

public class Target {
   public void execute(String request){
      System.out.println("Executing request: " + request);
   }
}
