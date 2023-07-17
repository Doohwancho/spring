package design_pattern.Behavioral.memento;

import java.util.ArrayList;
import java.util.List;

//메멘토의 '시간의 흐름 따라' 상태 관리자. version control system 느낌.  
public class CareTaker {
   private List<Memento> mementoList = new ArrayList<Memento>();

   public void add(Memento state){
      mementoList.add(state);
   }

   public Memento get(int index){
      return mementoList.get(index);
   }
}
