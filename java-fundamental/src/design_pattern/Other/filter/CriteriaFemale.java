package design_pattern.Other.filter;

import java.util.ArrayList;
import java.util.List;

public class CriteriaFemale implements Criteria {

	   @Override
	   public List<Person> meetCriteria(List<Person> persons) {
	      List<Person> femalePersons = new ArrayList<Person>(); 
	      
	      for (Person person : persons) {
	         if(person.getGender().equalsIgnoreCase("FEMALE")){ //if 조건문으로 걸러준
	            femalePersons.add(person);
	         }
	      }
	      return femalePersons;
	   }
	}
