package _8_design_pattern.Other.filter;

import java.util.ArrayList;
import java.util.List;

public class Main {
   public static void main(String[] args) {
      List<Person> persons = new ArrayList<Person>(); //여기에 객체 담고 Criteria 기준대로 filter

      persons.add(new Person("Robert","Male", "Single"));
      persons.add(new Person("John", "Male", "Married"));
      persons.add(new Person("Laura", "Female", "Married"));
      persons.add(new Person("Diana", "Female", "Single"));
      persons.add(new Person("Mike", "Male", "Single"));
      persons.add(new Person("Bobby", "Male", "Single"));

      Criteria male = new CriteriaMale();
      Criteria female = new CriteriaFemale();
      Criteria single = new CriteriaSingle();
      Criteria singleMale = new AndCriteria(single, male); //두 criterias AND
      Criteria singleOrFemale = new OrCriteria(single, female); //두 criterias OR

      System.out.println("Males: ");
      printPersons(male.meetCriteria(persons));

      System.out.println("\nFemales: ");
      printPersons(female.meetCriteria(persons));

      System.out.println("\nSingle Males: ");
      printPersons(singleMale.meetCriteria(persons));

      System.out.println("\nSingle Or Females: ");
      printPersons(singleOrFemale.meetCriteria(persons));
   }

   public static void printPersons(List<Person> persons){
   
      for (Person person : persons) {
         System.out.println("Person : [ Name : " + person.getName() + ", Gender : " + person.getGender() + ", Marital Status : " + person.getMaritalStatus() + " ]");
      }
   }    
}

/*

---
structure

- Criteria
	- CriteriaMale
	- CriteriaFemale
	- CriteriaSingle
	- AndCriteria(Criteria1 AND Criteria2)
	- OrCriteria(Criteria1 OR Criteria2)


*/
