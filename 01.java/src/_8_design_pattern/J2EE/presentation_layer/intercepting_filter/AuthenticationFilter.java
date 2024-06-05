package _8_design_pattern.J2EE.presentation_layer.intercepting_filter;

public class AuthenticationFilter implements Filter {
   public void execute(String request){
      System.out.println("Authenticating request: " + request);
   }
}
