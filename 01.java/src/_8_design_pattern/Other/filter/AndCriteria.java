package _8_design_pattern.Other.filter;

import java.util.List;

public class AndCriteria implements Criteria {

   //두 criterias AND
   private Criteria criteria;
   private Criteria otherCriteria;

   public AndCriteria(Criteria criteria, Criteria otherCriteria) {
      this.criteria = criteria;
      this.otherCriteria = otherCriteria; 
   }

   @Override
   public List<Person> meetCriteria(List<Person> persons) {    //두 criterias AND
   
      List<Person> firstCriteriaPersons = criteria.meetCriteria(persons);		
      return otherCriteria.meetCriteria(firstCriteriaPersons);
   }
}