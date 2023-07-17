package design_pattern.Behavioral.memento;

//상태가 바뀌는 객체 
public class Memento {
   private String state;

   public Memento(String state){
      this.state = state;
   }

   public String getState(){
      return state;
   }	
}
