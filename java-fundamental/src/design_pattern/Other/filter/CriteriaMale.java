package design_pattern.Other.filter;

import java.util.ArrayList;
import java.util.List;

public class CriteriaMale implements Criteria {

   @Override
   public List<Person> meetCriteria(List<Person> persons) {
      List<Person> malePersons = new ArrayList<Person>(); 
      
      for (Person person : persons) {
         if(person.getGender().equalsIgnoreCase("MALE")){ //if 조건문으로 걸러준
            malePersons.add(person);
         }
      }
      return malePersons;
   }
}
