package _8_design_pattern.Other.filter;

import java.util.List;

public class OrCriteria implements Criteria {

   //두 criterias OR
   private Criteria criteria;
   private Criteria otherCriteria;

   public OrCriteria(Criteria criteria, Criteria otherCriteria) {
      this.criteria = criteria;
      this.otherCriteria = otherCriteria; 
   }

   @Override
   public List<Person> meetCriteria(List<Person> persons) {    //두 criterias OR
      List<Person> firstCriteriaItems = criteria.meetCriteria(persons);
      List<Person> otherCriteriaItems = otherCriteria.meetCriteria(persons);

      for (Person person : otherCriteriaItems) {
         if(!firstCriteriaItems.contains(person)){
            firstCriteriaItems.add(person);
         }
      }	
      return firstCriteriaItems;
   }
}
