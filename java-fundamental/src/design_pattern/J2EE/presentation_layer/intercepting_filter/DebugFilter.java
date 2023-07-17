package design_pattern.J2EE.presentation_layer.intercepting_filter;

public class DebugFilter implements Filter {
   public void execute(String request){
      System.out.println("request log: " + request);
   }
}
